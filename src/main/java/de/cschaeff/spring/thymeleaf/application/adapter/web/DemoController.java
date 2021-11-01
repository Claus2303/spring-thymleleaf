package de.cschaeff.spring.thymeleaf.application.adapter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/leaders")
    public String leadersPage(){
        return "leaders";
    }
}
