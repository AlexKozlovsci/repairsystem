package repairSystem.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.dao.ClientRepository;
import repairSystem.dao.UserRepository;
import repairSystem.dao.WorkorderRepository;
import repairSystem.model.Client;
import repairSystem.model.Workorder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Алексей on 29.04.2017.
 */

@Controller
public class ManagerController {
    private static final Logger log = Logger.getLogger(ManagerController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkorderRepository workorderRepository;

    @Autowired
    private ClientRepository clientRepository;


    @RequestMapping(value = "/manager/", method = RequestMethod.GET)
    public ModelAndView index(){

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        repairSystem.model.User u = userRepository.findByLogin(authUser.getUsername());

        ModelAndView mav = new ModelAndView();
        List<Workorder> workorders = (List<Workorder>) workorderRepository.findAllByIdManager(u.getId());
        Workorder item;
        Client client;
        repairSystem.model.User engineer;
        String clientName, engineerName;
        Map<String, String> mapClients = new HashMap<String, String>();
        Map<String, String> mapEngineers = new HashMap<String, String>();
        for (Iterator<Workorder> i = workorders.iterator(); i.hasNext();) {
            item = i.next();
            Integer temp = (int)item.getId();
            String id = temp.toString();
            client = clientRepository.findById(item.getId_client());
            clientName = client.getName().concat(" ").concat(client.getSecondname());
            mapClients.put(id, clientName);
            engineer = userRepository.findById(item.getId_engineer());
            engineerName = engineer.getName().concat(" ").concat(engineer.getSecondname());
            mapEngineers.put(id, engineerName);
        }
        mav.addObject("orders", workorders);
        mav.addObject("clients", mapClients);
        mav.addObject("engineers", mapEngineers);
        mav.setViewName("manager/index");
        return mav;
    }

    @RequestMapping(value = "/manager/order{id}", method = RequestMethod.GET)
    public ModelAndView showOrder(@ModelAttribute Workorder order, final HttpServletRequest req){
        final Integer workOrderId = Integer.valueOf(req.getParameter("id"));
        List<repairSystem.model.User> engineers = (List<repairSystem.model.User>) userRepository.findAllByRole("ROLE_ENGINEER");
        String status = "";
        String id_engineer = "";
        Workorder wo = (Workorder) workorderRepository.findById(workOrderId);
        if (wo == null) {
            return new ModelAndView("404");
        }
        ModelAndView mav = new ModelAndView();

        Client client = clientRepository.findById(wo.getId_client());
        String clientName = client.getName().concat(" ").concat(client.getSecondname());

        repairSystem.model.User engineer = userRepository.findById(wo.getId_engineer());
        String engineerName = engineer.getName().concat(" ").concat(engineer.getSecondname());

        mav.addObject("client", clientName);
        mav.addObject("engineer", engineerName);
        mav.addObject("status", status);
        mav.addObject("id_engineer", id_engineer);
        mav.addObject("engineers", engineers);
        mav.addObject("workorder", wo);
        mav.setViewName("manager/order");
        return mav;
    }

    @RequestMapping(value = "/manager/order/changeOrderStatus", method = RequestMethod.POST)
    public ModelAndView changeOrderStatus(@ModelAttribute Workorder workorder, String status, HttpServletRequest request){
        switch (status) {
            case "1":
                status = "Open";
                break;
            case "2":
                status = "In work";
                break;
            case "3":
                status = "Complete";
                break;
            case "4":
                status = "Closed";
                break;
        }
        Workorder wo = workorderRepository.findById(workorder.getId());
        wo.setStatus(status);
        workorderRepository.save(wo);
        ModelAndView mav = new ModelAndView();
        Integer temp = (int)workorder.getId();
        String id = temp.toString();
        String referer = request.getHeader("Referer");
        mav.setViewName("redirect:"+ referer);
        return mav;
    }

    @RequestMapping(value = "/manager/order/changeOrderEngineer", method = RequestMethod.POST)
    public ModelAndView changeOrderEngineer(@ModelAttribute Workorder workorder, String id_engineer, HttpServletRequest request){
        Workorder wo = workorderRepository.findById(workorder.getId());
        long eng_id = (long)Integer.parseInt(id_engineer);
        wo.setId_engineer(eng_id);
        workorderRepository.save(wo);
        ModelAndView mav = new ModelAndView();
        String referer = request.getHeader("Referer");
        mav.setViewName("redirect:"+ referer);
        return mav;
    }


    @RequestMapping(value = "/manager/addOrder", method = RequestMethod.GET)
    public ModelAndView addPart(@ModelAttribute Workorder workorder, @ModelAttribute Client client){
        List<repairSystem.model.User> engineers = (List<repairSystem.model.User>) userRepository.findAllByRole("ROLE_ENGINEER");
        ModelAndView mav = new ModelAndView();
        String id_engineer = "";
        mav.addObject("id_engineer", id_engineer);
        mav.addObject("engineers", engineers);
        mav.setViewName("manager/addOrder");
        return mav;
    }

    @RequestMapping(value = "/manager/addOrder", method = RequestMethod.POST)
    public ModelAndView addPart(@ModelAttribute Workorder workorder, @ModelAttribute Client client, String id_engineer,  Model model){
        clientRepository.save(client);

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        repairSystem.model.User u = userRepository.findByLogin(authUser.getUsername());

        workorder.setId_manager(u.getId());
        workorder.setId_client(client.getId());
        workorder.setStatus("Open");
        long temp = (long)Integer.parseInt(id_engineer);
        workorder.setId_engineer(temp);
        Date curDate = new Date();

        String curTime = new SimpleDateFormat("yyyy-MM-dd").format(curDate);
        workorder.setCreate_at(curTime);

        workorderRepository.save(workorder);

        return new ModelAndView("redirect:/manager/");
    }

    @RequestMapping(value = "/manager/editOrder", method = RequestMethod.GET, params = {"id"})
    public ModelAndView editOrder(@ModelAttribute Workorder order, final HttpServletRequest req) {
        final Integer workOrderId = Integer.valueOf(req.getParameter("id"));
        Workorder wo = (Workorder) workorderRepository.findById(workOrderId);
        if (wo == null) {
            return new ModelAndView("404");
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("workorder", wo);
        mav.setViewName("manager/editOrder");
        return mav;
    }


    @RequestMapping(value = "/manager/editOrder", method = RequestMethod.POST)
    public ModelAndView editOrder(@ModelAttribute Workorder workorder){
        if (workorder.getId() != 0){
            Workorder wo = workorderRepository.findById(workorder.getId());
            wo.setDescription(workorder.getDescription());
            wo.setProblem(workorder.getProblem());
            workorderRepository.save(wo);
            return new ModelAndView("redirect:/manager/");
        }
        else {
            return new ModelAndView("404");
        }
    }

    @RequestMapping(value = "/manager/deleteOrder", method = RequestMethod.POST)
    public ModelAndView deleteParts(@ModelAttribute Workorder workorder){
        long id = workorder.getId();
        Workorder wo = (Workorder) workorderRepository.findById(id);
        if (wo == null) {
            return new ModelAndView("404");
        }
        workorderRepository.delete(wo);
        return new ModelAndView("redirect:/manager/");
    }

    @RequestMapping(value="/manager/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/auth/login";
    }
}
