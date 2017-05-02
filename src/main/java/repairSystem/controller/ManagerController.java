package repairSystem.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.dao.UserRepository;

/**
 * Created by Алексей on 29.04.2017.
 */

@Controller
public class ManagerController {
    private static final Logger log = Logger.getLogger(ManagerController.class);
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/manager/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("manager/index");
        return mav;
    }

}
