package repairSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Vlad on 01.05.2017.
 */

@Controller
public class EngineerController {

    @RequestMapping(value = "/engineer/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/index");
        return mav;
    }

    @RequestMapping(value = "/engineer/order", method = RequestMethod.GET)
    public ModelAndView order(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/order");
        return mav;
    }
    @RequestMapping(value = "/engineer/DiagnosticsList", method = RequestMethod.GET)
    public ModelAndView DiagnosticsList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/DiagnosticsList");
        return mav;

    }
    @RequestMapping(value = "/engineer/Report", method = RequestMethod.GET)
    public ModelAndView Report(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/Report");
        return mav;
    }
    @RequestMapping(value = "/engineer/WorkItems", method = RequestMethod.GET)
    public ModelAndView WorkItems(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("engineer/WorkItems");
        return mav;
    }
}
