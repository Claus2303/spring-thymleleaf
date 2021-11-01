package de.cschaeff.spring.thymeleaf.application.adapter.web;

import de.cschaeff.spring.thymeleaf.application.domain.bo.Partner;
import de.cschaeff.spring.thymeleaf.application.domain.service.PartnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/partner")
public class PartnerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PartnerService partnerService;
    @GetMapping("/list")
    public String listPartners(Model model, Principal principal){

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
        model.addAttribute("username", principal.getName());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //Wegen Problemen mit Thymleaf Tags am Server
        Set<String> roles = authentication.getAuthorities().stream()
                .map(r -> r.getAuthority()).collect(Collectors.toSet());
        model.addAttribute("rollen", roles.toString());
        return "list-partners";
    }
    @GetMapping("/suche")
    public String suchePartner(@RequestParam("suchName") String suchname,  Model model){

        List<Partner> partnerlist = partnerService.findByName(suchname);
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

    @GetMapping("/delete")
    public String delete(@RequestParam("partnerId") int id, Model model){
        partnerService.deleteById(id);
        return "redirect:/partner/list";
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

    //@PostConstruct
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
