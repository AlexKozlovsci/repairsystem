package repairSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.dao.UserRepository;
import repairSystem.model.User;

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView loginUser(HttpSession httpSession, @RequestBody User user) {
        User dbUser = userRepository.findByName(user.getName());
        ModelAndView mav = new ModelAndView();
        ModelAndView mavError = new ModelAndView();
        mavError.setViewName("error");
        mav.setViewName("test");
        String userPass = dbUser.getPassword().toString();
        if (dbUser != null && user.getPassword().equals(userPass)) {
            httpSession.setAttribute("currentUserAuthorityID", user.getRole());
            switch(user.getRole()) {
                case "admin":
                    mav.setViewName("adminMainPage");
                    break;
                case "engineer":
                    mav.setViewName("engineerMainPage");
                    break;
                case "manager":
                    mav.setViewName("managerMainPage");
                    break;
                default:
                    mavError.setViewName("error");
                    break;
            }
            return mav;
        } else {
            return mavError;
        }
    }
}