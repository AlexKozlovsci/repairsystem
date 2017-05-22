package repairSystem.config;

/**
 * Created by Алексей on 03.05.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import repairSystem.dao.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        HttpSession session = httpServletRequest.getSession();
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        repairSystem.model.User u = userRepository.findByLogin(authUser.getUsername());
        session.setAttribute("currentUserRole", u.getRole().substring(5,u.getRole().length()).toLowerCase());
        session.setAttribute("currentUser", u);
        session.setAttribute("currentUsername", u.getLogin());
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        String role = "/";
        switch (u.getRole()){
            case "ROLE_ADMIN":
                role = "/admin/";
                break;
            case "ROLE_MANAGER":
                role = "/manager/";
                break;
            case "ROLE_ENGINEER":
                role = "/engineer/";
                break;
        }
        httpServletResponse.sendRedirect(role);
    }
}