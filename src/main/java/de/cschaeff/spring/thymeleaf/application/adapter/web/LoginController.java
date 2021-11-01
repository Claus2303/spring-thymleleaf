package de.cschaeff.spring.thymeleaf.application.adapter.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Controller
public class LoginController {

    public static final String LOGIN_PAGE = "plain-login";

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){
        return LOGIN_PAGE;
    }

    @GetMapping("/showMyFailurePage")
    public String showMyFailurePage(Model model){
        model.addAttribute("loginError", true);
        return LOGIN_PAGE;
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage(){
        return "access-denied";
    }
}
