package de.cschaeff.spring.thymeleaf.application.adapter.web;

import de.cschaeff.spring.thymeleaf.application.domain.bo.Partner;
import de.cschaeff.spring.thymeleaf.application.domain.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/partner")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;
    @GetMapping("/list")
    public String listPartners(Model model){

        List<Partner> partnerlist = partnerService.findAll();
        List<PartnerDTO> dtolist =  new ArrayList<>();

        for (Partner partner: partnerlist){
            dtolist.add(PartnerDTO.builder().id(partner.getId())
                    .firstName(partner.getVorname())
                    .lastName(partner.getName())
                    .email(partner.getEmail())
                    .build());
        }
        model.addAttribute("partners", dtolist);
        return "list-partners";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        PartnerDTO dto = new PartnerDTO();

        model.addAttribute("partnerDTO", dto);
        return "partner/partner-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("partnerId") int id, Model model){
        Partner partner = partnerService.findById(id);
        model.addAttribute("partnerDTO", PartnerDTO.builder().id(partner.getId())
                .firstName(partner.getVorname())
                .lastName(partner.getName())
                .email(partner.getEmail())
                .build());
        return "partner/partner-form";
    }

    @PostMapping("/save")
    public  String savePartner(@ModelAttribute("partnerDTO") PartnerDTO partnerDTO){
        partnerService.save(Partner.builder()
                .id(partnerDTO.getId())
                .name(partnerDTO.getLastName())
                .vorname(partnerDTO.getFirstName())
                .email(partnerDTO.getEmail())
                .build());

        return "redirect:/partner/list";
    }

    @PostConstruct
    private void loadPartners() {

        partnerService.save(Partner.builder().id(1)
                .name("Schaeffner")
                .vorname("claus")
                .email("claus@schaeffner.de")
                .build());
        partnerService.save(Partner.builder().id(2)
                .name("Schaeffner")
                .vorname("Tanja")
                .email("claus@schaeffner.de")
                .build());
        partnerService.save(Partner.builder().id(3)
                .name("Schaeffner")
                .vorname("Philipp")
                .email("claus@schaeffner.de")
                .build());
    }
}
