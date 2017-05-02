package repairSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import repairSystem.dao.UserRepository;
import org.apache.log4j.Logger;
//import java.util.Objects;

@Controller
public class AuthController {
    private static final Logger log = Logger.getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*@RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String showAuth() {
        return "login";
    }*/

    /*@RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ModelAndView loginUser(HttpSession httpSession, @RequestBody User user) throws NoSuchAlgorithmException {
        User dbUser = userRepository.findByName(user.getLogin());
        ModelAndView mav = new ModelAndView();
        ModelAndView mavError = new ModelAndView();
        mavError.setViewName("404");
        mav.setViewName("index");

        String userPass = dbUser.getPassword().toString();
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(user.getPassword().getBytes(),0, user.getPassword().length());
        String md5Pass = new BigInteger(1, messageDigest.digest()).toString(16);
        if (md5Pass.length() < 32)
            md5Pass = "0" + md5Pass;

        if (dbUser != null && md5Pass.equals(userPass)) {
            httpSession.setAttribute("currentUserRole", dbUser.getRole());
            return mav;
        } else {
            return mavError;
        }
    }*/



    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}