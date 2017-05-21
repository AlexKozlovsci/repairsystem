package repairSystem.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.dao.DetailRepository;
import repairSystem.dao.PricelistRepository;
import repairSystem.dao.UserRepository;
import repairSystem.model.Detail;
import repairSystem.model.Pricelist;
import repairSystem.model.User;

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
    private PricelistRepository priceListRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelAndView getModelView(String model, JpaRepository repository){
        ModelAndView mav = new ModelAndView();
        List<Model> obj = (List<Model>) repository.findAll();
        mav.addObject(model, obj);
        mav.setViewName("admin/".concat(model));
        return mav;
    }

    /*@RequestMapping(value = "/admin/delete{model}", method = RequestMethod.GET, params = {"id"})
    public ModelAndView deleteParts(@ModelAttribute Model model, @PathVariable(value="model") String modelName, final HttpServletRequest req){
        ModelAndView mav = new ModelAndView();
        final Integer modelItemId = Integer.valueOf(req.getParameter("id"));
        if (modelName.equals("Parts")) {
            mav = deleteModel("parts", detailRepository, modelItemId);
        }
        else if (model.equals("PriceItem")) {
            mav = deleteModel("prices", priceListRepository, modelItemId);
        }
        else if (model.equals("User")) {
            mav = deleteModel("users", userRepository, modelItemId);
        }
        else {
            mav.setViewName("admin/404");
        }
        return mav;
    }

    private ModelAndView deleteModel(String model, JpaRepository repository, Integer modelItemId){
        Model modelItem = (Model) repository.findById(modelItemId);
        if (modelItem == null) {
            return new ModelAndView("404");
        }
        repository.delete(modelItem);
        return new ModelAndView("redirect:/admin/".concat(model));
    }*/

    @RequestMapping(value = "/admin/{model}", method = RequestMethod.GET)
    public ModelAndView AdminMappingModel(@PathVariable(value="model") String model){
        ModelAndView mav = new ModelAndView();
        if (model.equals("parts")) {
            mav = getModelView(model, detailRepository);
        }
        else if (model.equals("prices")) {
            mav = getModelView(model, priceListRepository);
        }
        else if (model.equals("users")) {
            mav = getModelView(model, userRepository);
        }
        else {
            mav.setViewName("404");
        }
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

    @RequestMapping(value = "/admin/addPriceItem", method = RequestMethod.GET)
    public ModelAndView addPriceItem(@ModelAttribute Pricelist priceList){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/addPriceItem");
        return mav;
    }

    @RequestMapping(value = "/admin/addPriceItem", method = RequestMethod.POST)
    public ModelAndView addPriceItem(@ModelAttribute Pricelist priceList, Model model){
        priceListRepository.save(priceList);
        return new ModelAndView("redirect:/admin/prices");
    }

    @RequestMapping(value = "/admin/editPriceItem", method = RequestMethod.GET, params = {"id"})
    public ModelAndView editPriceItem(@ModelAttribute Pricelist priceList, final HttpServletRequest req) {
        final Integer priceItemId = Integer.valueOf(req.getParameter("id"));
        Pricelist priceItem = (Pricelist) priceListRepository.findById(priceItemId);
        if (priceItem == null) {
            return new ModelAndView("404");
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("priceItem", priceItem);
        mav.setViewName("admin/editPriceItem");
        return mav;
    }

    @RequestMapping(value = "/admin/editPriceItem", method = RequestMethod.POST)
    public ModelAndView editPriceItem(@ModelAttribute Pricelist priceList){
        log.info(priceList.getId());
        if (priceList.getId() != 0){
            priceListRepository.save(priceList);
            return new ModelAndView("redirect:/admin/prices");
        }
        else {
            return new ModelAndView("404");
        }
    }

    @RequestMapping(value = "/admin/deletePriceItem", method = RequestMethod.GET, params = {"id"})
    public ModelAndView deletePriceItem(@ModelAttribute Pricelist pricelistItem, final HttpServletRequest req){
        final Integer priceItemId = Integer.valueOf(req.getParameter("id"));
        Pricelist priceItem = (Pricelist) priceListRepository.findById(priceItemId);
        if (priceItem == null) {
            return new ModelAndView("404");
        }
        priceListRepository.delete(priceItem);
        return new ModelAndView("redirect:/admin/prices");
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
    public ModelAndView addUser(@ModelAttribute User user){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/addUser");
        return mav;
    }

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user, Model model){
        user.setRole("ROLE_".concat(user.getRole().toUpperCase()));
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        user.setPassword(encoder.encodePassword(user.getPassword(),""));
        userRepository.save(user);
        return new ModelAndView("redirect:/admin/users");
    }


    @RequestMapping(value = "/admin/editUser", method = RequestMethod.GET, params = {"id"})
    public ModelAndView editUser(@ModelAttribute User user, final HttpServletRequest req) {
        final Integer userId = Integer.valueOf(req.getParameter("id"));
        User userItem = (User) userRepository.findById(userId);
        userItem.setRole(userItem.getRole().substring(5,userItem.getRole().length()).toLowerCase());
        if (userItem == null) {
            return new ModelAndView("404");
        }
        ModelAndView mav = new ModelAndView();
        String pass = "";
        mav.addObject("user", userItem);
        mav.addObject("pass", pass);
        mav.setViewName("admin/editUser");
        return mav;
    }

    @RequestMapping(value = "/admin/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user, String pass){
    log.info(pass);
        if (user.getId() != 0){
            user.setRole("ROLE_".concat(user.getRole().toUpperCase()));
            User oldUser = userRepository.findById(user.getId());
            if(pass.isEmpty()){
                user.setPassword(oldUser.getPassword());
            }else{
                Md5PasswordEncoder encoder = new Md5PasswordEncoder();
                user.setPassword(encoder.encodePassword(pass, ""));
            }
            userRepository.save(user);
            return new ModelAndView("redirect:/admin/users");
        }
        else {
            return new ModelAndView("404");
        }
    }


    @RequestMapping(value = "/admin/deleteUser", method = RequestMethod.GET, params = {"id"})
    public ModelAndView deleteUser(@ModelAttribute User user, final HttpServletRequest req){
        final Integer userId = Integer.valueOf(req.getParameter("id"));
        User userItem = (User) userRepository.findById(userId);
        if (userItem == null) {
            return new ModelAndView("404");
        }
        userRepository.delete(userItem);
        return new ModelAndView("redirect:/admin/users");
    }
}
