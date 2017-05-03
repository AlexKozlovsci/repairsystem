package repairSystem.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.dao.DetailRepository;
import repairSystem.model.Detail;

import java.util.List;

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

    private static final Logger log = Logger.getLogger(AdminController.class);
    @Autowired
    private DetailRepository detailRepository;

    @RequestMapping(value = "/admin/parts/", method = RequestMethod.GET)
    public ModelAndView parts(Model model){
        ModelAndView mav = new ModelAndView();
        List<Detail> det = (List<Detail>) detailRepository.findAll();
        mav.addObject("details", det);
        mav.setViewName("admin/parts");
        return mav;
    }

    @RequestMapping(value = "/admin/parts/", method = RequestMethod.POST)
    public ModelAndView parts(@ModelAttribute Detail detail, Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/parts");
        return mav;
    }

    @RequestMapping(value = "/admin/editParts/", method = RequestMethod.GET)
    public ModelAndView editParts(@ModelAttribute Detail detail, Detail d) {
        ModelAndView mav = new ModelAndView();
        Detail det = (Detail) detailRepository.findById(d.getId());
        mav.addObject("detail", det);
        log.info(det);
        log.info(d.getId());
        mav.setViewName("admin/editParts");
        return mav;
    }

    @RequestMapping(value = "/admin/editParts/", method = RequestMethod.POST)
    public ModelAndView editParts(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/editParts");
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
