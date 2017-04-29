package repairSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import repairSystem.dao.UserRepository;
import repairSystem.model.LoginForm;
import repairSystem.model.User;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//import java.util.Objects;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String showAuth(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ModelAndView loginUser(HttpSession httpSession, @Valid @ModelAttribute LoginForm user) throws NoSuchAlgorithmException {
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
    }
}