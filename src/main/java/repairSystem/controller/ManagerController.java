package repairSystem.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.dao.ClientRepository;
import repairSystem.dao.WorkorderRepository;
import repairSystem.dao.UserRepository;
import repairSystem.model.Client;
import repairSystem.model.Workorder;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        ModelAndView mav = new ModelAndView();
        mav.setViewName("manager/index");
        return mav;
    }

    @RequestMapping(value = "/manager/add_order", method = RequestMethod.GET)
    public ModelAndView addPart(@ModelAttribute Workorder workorder, @ModelAttribute Client client){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("manager/add_order");
        return mav;
    }

    @RequestMapping(value = "/manager/add_order", method = RequestMethod.POST)
    public ModelAndView addPart(@ModelAttribute Workorder workorder, @ModelAttribute Client client, Model model){
        clientRepository.save(client);

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        repairSystem.model.User u = userRepository.findByLogin(authUser.getUsername());

        workorder.setId_manager(u.getId());
        workorder.setId_client(client.getId());
        workorder.setStatus("Open");
        workorder.setComplete_at("2017-10-1");
        Date curDate = new Date();

        String curTime = new SimpleDateFormat("yyyy-MM-dd").format(curDate);
        workorder.setCreate_at(curTime);

        workorderRepository.save(workorder);

        return new ModelAndView("redirect:/manager/");
    }

}
