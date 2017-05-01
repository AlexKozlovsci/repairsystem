package repairSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Глеб on 01.05.2017.
 */

@Controller
public class AdminController {

    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/index");
        return mav;
    }

    @RequestMapping(value = "/admin/parts/", method = RequestMethod.GET)
    public ModelAndView parts(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/parts");
        return mav;
    }

    @RequestMapping(value = "/admin/users/", method = RequestMethod.GET)
    public ModelAndView users(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/users");
        return mav;
    }

    @RequestMapping(value = "/admin/prices/", method = RequestMethod.GET)
    public ModelAndView prises(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/prices");
        return mav;
    }

}
