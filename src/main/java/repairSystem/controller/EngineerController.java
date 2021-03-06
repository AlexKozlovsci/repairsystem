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
import repairSystem.config.CheckboxModel;
import repairSystem.dao.*;
import repairSystem.model.Detail;
import repairSystem.model.Pricelist;
import repairSystem.model.Workorder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Vlad on 01.05.2017.
 */

@Controller
public class EngineerController {

    private static final Logger log = Logger.getLogger(EngineerController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkorderRepository workorderRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private PricelistRepository pricelistRepository;

    private InputStream stream;

    @RequestMapping(value = "/engineer/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        repairSystem.model.User u = userRepository.findByLogin(authUser.getUsername());
        Map<String, String> mapManagers = new HashMap<String, String>();
        Workorder item;
        repairSystem.model.User manager;
        List<Workorder> workorders = (List<Workorder>) workorderRepository.findAllByIdEngineer(u.getId());
        for (Iterator<Workorder> i = workorders.iterator(); i.hasNext();) {

            item = i.next();
            Integer temp = (int)item.getId();
            String id = temp.toString();
            manager = userRepository.findById(item.getId_manager());
            String managerName = manager.getName().concat(" ").concat(manager.getSecondname());
            mapManagers.put(id, managerName);
        }
        mav.addObject("orders", workorders);
        mav.addObject("managers", mapManagers);
        mav.setViewName("engineer/index");
        return mav;
    }

    @RequestMapping(value = "/engineer/order", method = RequestMethod.GET, params = {"id"})
    public ModelAndView order(@ModelAttribute Workorder workorder, final HttpServletRequest req){
        final Integer orderId = Integer.valueOf(req.getParameter("id"));
        if (!workorderRepository.existsById(orderId)){
            return new ModelAndView("404");
        }
        workorder = (Workorder) workorderRepository.findById(orderId);
        repairSystem.model.User manager = userRepository.findById(workorder.getId_manager());
        String managerName = manager.getName().concat(" ").concat(manager.getSecondname());
        repairSystem.model.Client client = clientRepository.findById(workorder.getId_manager());
        String clientName = client.getName().concat(" ").concat(client.getSecondname());

        Set<Detail> details =  workorder.getDetail();
        Set<Pricelist> pricelists =  workorder.getPricelists();

        int totalCost = 0;
        for (Detail detail: details)
            totalCost += detail.getCost();
        for (Pricelist pricelist: pricelists)
            totalCost += pricelist.getCost();

        String status = "";
        ModelAndView mav = new ModelAndView();
        mav.addObject("order", workorder);
        mav.addObject("manager", managerName);
        mav.addObject("client", clientName);
        mav.addObject("details", details);
        mav.addObject("prices", pricelists);
        mav.addObject("totalCost", totalCost);
        mav.addObject("status", status);
        mav.setViewName("engineer/order");
        return mav;
    }



    @RequestMapping(value = "/engineer/addDetailToOrder", method = RequestMethod.GET)
    public ModelAndView addDetailToOrder(@ModelAttribute  Detail detail, int orderId){
        ModelAndView mav = new ModelAndView();
        Workorder workorder = (Workorder) workorderRepository.findById(orderId);
        List<Detail> parts = (List<Detail>) detailRepository.findAll();
        Set<Detail> existDetails = workorder.getDetail();
        for (Detail detailItem: existDetails) {
            if (parts.contains(detailItem)) {
                parts.remove(detailItem);
            }
        }
        CheckboxModel checkboxModel = new CheckboxModel();
        mav.addObject("checkboxModel", checkboxModel);
        mav.addObject("parts", parts);
        mav.addObject("orderId", orderId);
        mav.setViewName("/engineer/addDetailToOrder");
        return mav;
    }

    @RequestMapping(value = "/engineer/addDetailToOrder", method = RequestMethod.POST)
    public ModelAndView addDetailToOrder(@ModelAttribute(value="checkboxModel") CheckboxModel checkboxModel, int orderId, Model model){
        List<String> checkedItems = checkboxModel.getCheckedItems();
        Workorder workorder = (Workorder) workorderRepository.findById(orderId);
        Detail detailItem;
        Set<Detail> details = new HashSet<Detail>(workorder.getDetail());
        for (String id:checkedItems) {
            detailItem = (Detail) detailRepository.findById(Integer.parseInt(id));
            details.add(detailItem);
        }
        workorder.setDetail(details);
        workorderRepository.save(workorder);
        ModelAndView mav = new ModelAndView();
        Integer temp = (int)orderId;
        String id = temp.toString();
        return new ModelAndView("redirect:/engineer/order?id=".concat(id));
    }

    @RequestMapping(value = "/engineer/deleteDetailFromOrder", method = RequestMethod.POST)
    public ModelAndView deleteDetailFromOrder(@ModelAttribute Detail detail, int orderId){
        long id = detail.getId();
        if (!detailRepository.existsById(id)){
            return new ModelAndView("404");
        }
        if (!workorderRepository.existsById(orderId)){
            return new ModelAndView("404");
        }
        Detail detailItem = (Detail) detailRepository.findById(id);
        Workorder workorder = (Workorder) workorderRepository.findById(orderId);
        workorder.details.remove(detailItem);
        workorderRepository.save(workorder);
        Integer temp = (int)orderId;
        String orderIdstr = temp.toString();
        return new ModelAndView("redirect:/engineer/order?id=".concat(orderIdstr));
    }

    @RequestMapping(value = "/engineer/deleteActionFromOrder", method = RequestMethod.POST)
    public ModelAndView deleteActionFromOrder(@ModelAttribute Pricelist priceList, int orderId){
        long id = priceList.getId();
        Pricelist actionItem = (Pricelist) pricelistRepository.findById(id);
        Workorder workorder = (Workorder) workorderRepository.findById(orderId);
        workorder.pricelists.remove(actionItem);
        workorderRepository.save(workorder);
        Integer temp = (int)orderId;
        String orderIdstr = temp.toString();
        return new ModelAndView("redirect:/engineer/order?id=".concat(orderIdstr));
    }

    @RequestMapping(value = "/engineer/addActionToOrder", method = RequestMethod.GET)
    public ModelAndView addActionToOrder(@ModelAttribute  Pricelist priceList, int orderId){
        ModelAndView mav = new ModelAndView();
        Workorder workorder = (Workorder) workorderRepository.findById(orderId);
        List<Pricelist> action = (List<Pricelist>) pricelistRepository.findAll();
        Set<Pricelist> existActions = workorder.getPricelists();
        for (Pricelist actionItem: existActions) {
            if (action.contains(actionItem)) {
                action.remove(actionItem);
            }
        }
        CheckboxModel checkboxModel = new CheckboxModel();
        mav.addObject("checkboxModel", checkboxModel);
        mav.addObject("prices", action);
        mav.addObject("orderId", orderId);
        mav.setViewName("/engineer/addActionToOrder");
        return mav;
    }

    @RequestMapping(value = "/engineer/addActionToOrder", method = RequestMethod.POST)
    public ModelAndView addActionToOrder(@ModelAttribute(value="checkboxModel") CheckboxModel checkboxModel, int orderId, Model model){
        List<String> checkedItems = checkboxModel.getCheckedItems();
        Workorder workorder = (Workorder) workorderRepository.findById(orderId);
        Pricelist actionItem;
        Set<Pricelist> actions = new HashSet<Pricelist>(workorder.getPricelists());
        for (String id:checkedItems) {
            actionItem = (Pricelist) pricelistRepository.findById(Integer.parseInt(id));
            actions.add(actionItem);
        }
        workorder.setPricelists(actions);
        workorderRepository.save(workorder);
        ModelAndView mav = new ModelAndView();
        Integer temp = (int)orderId;
        String orderIdstr = temp.toString();
        return new ModelAndView("redirect:/engineer/order?id=".concat(orderIdstr));
    }

    @RequestMapping(value = "/engineer/order/changeOrderStatus", method = RequestMethod.POST)
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
            default:
                status = "Closed";
                break;
        }
        Workorder wo = workorderRepository.findById(workorder.getId());
        wo.setStatus(status);
        ModelAndView mav = new ModelAndView();
        boolean error = false;
        try{
            workorderRepository.save(wo);
        }
        catch(Exception e){
            String referer = request.getHeader("Referer");
            mav.setViewName("redirect:"+ referer);
            error = true;
        }
        finally {
            mav.addObject("error", error);
            return mav;
        }
    }



    @RequestMapping(value = "/engineer/order/createDocument", method = RequestMethod.POST)
    public ModelAndView createDocument(@ModelAttribute Workorder workorder, String document, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        switch (document) {
            case "1":
                document = "diagnostic";
                try {
                    //stream = ByteArrayInputStream();
                }
                catch (Exception e){
                    mav.setViewName("404");
                    return mav;
                }
                break;
            case "2":
                document = "report";
                break;
        }
        return mav;
    }

    @RequestMapping(value="/engineer/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/auth/login";
    }
}