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
import repairSystem.dao.PricelistRepository;
import repairSystem.model.Detail;
import repairSystem.model.Pricelist;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private PricelistRepository pricelistRepository;

    @RequestMapping(value = "/admin/parts", method = RequestMethod.GET)
    public ModelAndView parts(){
        ModelAndView mav = new ModelAndView();
        List<Detail> det = (List<Detail>) detailRepository.findAll();
        mav.addObject("details", det);
        mav.setViewName("admin/parts");
        return mav;
    }

    @RequestMapping(value = "/admin/parts", method = RequestMethod.POST)
    public ModelAndView parts(@ModelAttribute Detail detail) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/parts");
        return mav;
    }

    @RequestMapping(value = "/admin/addPart", method = RequestMethod.GET)
    public ModelAndView addPart(@ModelAttribute Detail detail){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/addPart");
        return mav;
    }

    @RequestMapping(value = "/admin/addPart", method = RequestMethod.POST)
    public ModelAndView addPart(@ModelAttribute Detail detail, Model model){
        detailRepository.save(detail);
        return new ModelAndView("redirect:/admin/parts");
    }

    @RequestMapping(value = "/admin/editPart", method = RequestMethod.GET, params = {"id"})
    public ModelAndView editPart(@ModelAttribute Detail detail, final HttpServletRequest req) {
        final Integer detailId = Integer.valueOf(req.getParameter("id"));
        Detail det = (Detail) detailRepository.findById(detailId);
        if (det == null) {
            return new ModelAndView("404");
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("detail", det);
        mav.setViewName("admin/editPart");
        return mav;
    }

    @RequestMapping(value = "/admin/editPart", method = RequestMethod.POST)
    public ModelAndView editPart(@ModelAttribute Detail detail){
        if (detail.getId() != 0){
            detailRepository.save(detail);
            return new ModelAndView("redirect:/admin/parts");
        }
        else {
            return new ModelAndView("404");
        }
    }

    @RequestMapping(value = "/admin/deletePart", method = RequestMethod.GET, params = {"id"})
    public ModelAndView deleteParts(@ModelAttribute Detail detail, final HttpServletRequest req){
        final Integer detailId = Integer.valueOf(req.getParameter("id"));
        Detail det = (Detail) detailRepository.findById(detailId);
        if (det == null) {
            return new ModelAndView("404");
        }
        detailRepository.delete(det);
        return new ModelAndView("redirect:/admin/parts");
    }

    @RequestMapping(value = "/admin/prices", method = RequestMethod.GET)
    public ModelAndView prises(){
        ModelAndView mav = new ModelAndView();
        List<Pricelist> prices = (List<Pricelist>) pricelistRepository.findAll();
        mav.addObject("priceItems", prices);
        mav.setViewName("admin/prices");
        return mav;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ModelAndView users(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/users");
        return mav;
    }

}
