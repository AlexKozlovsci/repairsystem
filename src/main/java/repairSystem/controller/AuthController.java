package repairSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repairSystem.dao.UserRepository;
//import java.util.Objects;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showAuth() {
        //ModelAndView mav = new ModelAndView();
        //mav.setViewName("test");
        return "test";
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginUser(HttpSession httpSession, @RequestBody User user) {
        User dbUser = userRepository.findByName(user.getName());
        ModelAndView mav = new ModelAndView();
        ModelAndView mavError = new ModelAndView();
        mavError.setViewName("error");
        mav.setViewName("test");
        String userPass = dbUser.getPassword().toString();
        if (dbUser != null && user.getPassword().equals(userPass)) {
            //httpSession.setAttribute("currentUserName", user.getName());
            //httpSession.setAttribute("currentUserAuthorityID", user.getAuthorityId());
            return mav;
        } else {
            return mavError;
        }
    }*/
}