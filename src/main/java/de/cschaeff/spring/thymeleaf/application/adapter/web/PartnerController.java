package de.cschaeff.spring.thymeleaf.application.adapter.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/partner")
public class PartnerController {

    @GetMapping("/list")
    public String listPartners(Model model){
        Partner partner1 = new Partner(1L, "Claus","Schaeffner", "claus@schaeffner.de");
        Partner partner2 = new Partner(2L, "Tanja","Schaeffner", "tanja@schaeffner.de");
        Partner partner3 = new Partner(3L, "Philipp","Schaeffner", "philipp@schaeffner.de");

        List<Partner> list = new ArrayList<>();

        list.add(partner1);
        list.add(partner2);
        list.add(partner3);

        model.addAttribute("partners", list);
        return "list-partners";
    }
}
