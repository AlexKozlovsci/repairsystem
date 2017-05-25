package repairSystem.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import repairSystem.dao.WorkorderRepository;
import repairSystem.model.Detail;
import repairSystem.model.Pricelist;
import repairSystem.model.User;
import repairSystem.model.Workorder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Глеб on 01.05.2017.
 */

@Controller
public class AdminController {

    private static final Logger log = Logger.getLogger(AdminController.class);
    @Autowired
    private DetailRepository detailRepository;

    @Autowired
    private PricelistRepository priceListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkorderRepository workorderRepository;

    @RequestMapping(value = "/admin/", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        org.springframework.security.core.userdetails.User authUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        repairSystem.model.User u = userRepository.findByLogin(authUser.getUsername());
        String name = u.getName().concat(" ").concat(u.getSecondname());
        mav.addObject("name", name);
        mav.setViewName("admin/index");
        return mav;
    }

    private ModelAndView getModelView(String model, JpaRepository repository){
        ModelAndView mav = new ModelAndView();
        List<Model> obj = (List<Model>) repository.findAll();
        mav.addObject(model, obj);
        mav.setViewName("admin/".concat(model));
        return mav;
    }

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

    @RequestMapping(value = "/admin/addUser", method = RequestMethod.GET)
    public ModelAndView addUser(@ModelAttribute User user){
        ModelAndView mav = new ModelAndView();
        List<String> roles = Arrays.asList("admin", "manager", "engineer");
        String role = "";
        mav.addObject("role", role);
        mav.addObject("roles", roles);
        mav.setViewName("admin/addUser");
        return mav;
    }


    @RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user, Model model, String role){
        user.setRole("ROLE_".concat(role.toUpperCase()));
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        user.setPassword(encoder.encodePassword(user.getPassword(),""));
        ModelAndView mav = new ModelAndView();
        boolean error = false;
        mav.setViewName("redirect:/admin/users");
        try{
            userRepository.save(user);
        }
        catch(Exception e){
            List<String> roles = Arrays.asList("admin", "manager", "engineer");
            mav.addObject("roles", roles);
            error = true;
            mav.setViewName("/admin/addUser");
        }
        finally {
            mav.addObject("error", error);
            return mav;
        }
    }

    @RequestMapping(value = "/admin/editUser", method = RequestMethod.GET, params = {"id"})
    public ModelAndView editUser(@ModelAttribute User user, final HttpServletRequest req) {
        final Integer userId = Integer.valueOf(req.getParameter("id"));
        if (!userRepository.existsById(userId)){
            return new ModelAndView("404");
        }
        User userItem = (User) userRepository.findById(userId);
        userItem.setRole(userItem.getRole().substring(5,userItem.getRole().length()).toLowerCase());
        ModelAndView mav = new ModelAndView();
        String pass = "";
        List<String> roles = Arrays.asList("admin", "manager", "engineer");
        String role = "";
        mav.addObject("role", role);
        mav.addObject("roles", roles);
        mav.addObject("user", userItem);
        mav.addObject("pass", pass);
        mav.setViewName("admin/editUser");
        return mav;
    }

    @RequestMapping(value = "/admin/editUser", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user, String pass, String role){
        user.setRole("ROLE_".concat(role.toUpperCase()));
        User oldUser = userRepository.findById(user.getId());
        if(pass.isEmpty()){
            user.setPassword(oldUser.getPassword());
        }else{
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            user.setPassword(encoder.encodePassword(pass, ""));
        }
        ModelAndView mav = new ModelAndView();
        boolean error = false;
        mav.setViewName("redirect:/admin/users");
        try{
            userRepository.save(user);
        }
        catch(Exception e){
            List<String> roles = Arrays.asList("admin", "manager", "engineer");
            mav.addObject("roles", roles);
            error = true;
            mav.setViewName("/admin/editUser");
        }
        finally {
            mav.addObject("error", error);
            return mav;
        }

    }

    @RequestMapping(value = "/admin/deleteUser", method = RequestMethod.POST)
    public ModelAndView deleteUser(@ModelAttribute User user){
        long id = user.getId();
        if (!userRepository.existsById(id)){
            return new ModelAndView("404");
        }
        User userItem = (User) userRepository.findById(id);
        userRepository.delete(userItem);
        return new ModelAndView("redirect:/admin/users");
    }




    @RequestMapping(value = "/admin/addPart", method = RequestMethod.GET)
    public ModelAndView addPart(@ModelAttribute Detail detail){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/addPart");
        return mav;
    }

    @RequestMapping(value = "/admin/addPart", method = RequestMethod.POST)
    public ModelAndView addPart(@ModelAttribute Detail detail, Model model){
        ModelAndView mav = new ModelAndView();
        boolean error = false;
        mav.setViewName("redirect:/admin/parts");
        try{
            detailRepository.save(detail);
        }
        catch(Exception e){
            error = true;
            mav.setViewName("/admin/addPart");
        }
        finally {
            mav.addObject("error", error);
            return mav;
        }
    }

    @RequestMapping(value = "/admin/editPart", method = RequestMethod.GET, params = {"id"})
    public ModelAndView editPart(@ModelAttribute Detail detail, final HttpServletRequest req) {
        final Integer detailId = Integer.valueOf(req.getParameter("id"));
        if (!detailRepository.existsById(detailId)){
            return new ModelAndView("404");
        }
        Detail det = (Detail) detailRepository.findById(detailId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("detail", det);
        mav.setViewName("admin/editPart");
        return mav;
    }

    @RequestMapping(value = "/admin/editPart", method = RequestMethod.POST)
    public ModelAndView editPart(@ModelAttribute Detail detail){
        ModelAndView mav = new ModelAndView();
        boolean error = false;
        mav.setViewName("redirect:/admin/parts");
        try{
            detailRepository.save(detail);
        }
        catch(Exception e){
            error = true;
            mav.setViewName("/admin/editPart");
        }
        finally {
            mav.addObject("error", error);
            return mav;
        }
    }

    @RequestMapping(value = "/admin/deletePart", method = RequestMethod.POST)
    public ModelAndView deleteParts(@ModelAttribute Detail detail){
        long id = detail.getId();
        if (!detailRepository.existsById(id)){
            return new ModelAndView("404");
        }
        Detail det = (Detail) detailRepository.findById(id);
        List<Workorder> wlist = (List<Workorder>) workorderRepository.findAll();
        for (Workorder w:det.workorder) {
            w.details.remove(det);
        }
        detailRepository.delete(det);
        return new ModelAndView("redirect:/admin/parts");
    }




    @RequestMapping(value = "/admin/addPriceItem", method = RequestMethod.GET)
    public ModelAndView addPriceItem(@ModelAttribute Pricelist priceList){
        ModelAndView mav = new ModelAndView();
        mav.addObject("priceList", priceList);
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
        if (!priceListRepository.existsById(priceItemId)){
            return new ModelAndView("404");
        }
        Pricelist priceItem = (Pricelist) priceListRepository.findById(priceItemId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("priceItem", priceItem);
        mav.setViewName("admin/editPriceItem");
        return mav;
    }

    @RequestMapping(value = "/admin/editPriceItem", method = RequestMethod.POST)
    public ModelAndView editPriceItem(@ModelAttribute Pricelist priceList){
        priceListRepository.save(priceList);
        return new ModelAndView("redirect:/admin/prices");
    }

    @RequestMapping(value = "/admin/deletePriceItem", method = RequestMethod.POST)
    public ModelAndView deletePriceItem(@ModelAttribute Pricelist pricelistItem){
        long id = pricelistItem.getId();
        if (!priceListRepository.existsById(id)){
            return new ModelAndView("404");
        }
        Pricelist priceItem = (Pricelist) priceListRepository.findById(id);

        //Detail det = (Detail) detailRepository.findById(id);
        List<Workorder> wlist = (List<Workorder>) workorderRepository.findAll();
        for (Workorder w:priceItem.workorder) {
            w.pricelists.remove(priceItem);
        }
        priceListRepository.delete(priceItem);
        return new ModelAndView("redirect:/admin/prices");
    }



    @RequestMapping(value="/admin/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/auth/login";
    }
}
