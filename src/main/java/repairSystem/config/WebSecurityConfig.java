package repairSystem.config;

/**
 * Created by Алексей on 01.05.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public CustomAuthenticationSuccessHandler successHandler() {
        CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();
        return successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/design/**").permitAll()
                .antMatchers("/document/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')")
                .antMatchers("/engineer/**").access("hasRole('ROLE_ENGINEER')")
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/login")
                .successHandler(successHandler())
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new Md5PasswordEncoder());
    }




}
