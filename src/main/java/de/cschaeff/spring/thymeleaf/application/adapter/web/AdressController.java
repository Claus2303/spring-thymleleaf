package de.cschaeff.spring.thymeleaf.application.adapter.web;

import de.cschaeff.spring.thymeleaf.application.domain.bo.Adress;
import de.cschaeff.spring.thymeleaf.application.domain.bo.Partner;
import de.cschaeff.spring.thymeleaf.application.domain.service.AdressService;
import de.cschaeff.spring.thymeleaf.application.domain.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/partner/adress")
public class AdressController {

    @Autowired
    AdressService adressService;

    @Autowired
    PartnerService partnerService;

    @GetMapping("/list")
    public String listAdresses(@RequestParam("partnerId") int id, Model model){

        List<Adress> adresslist = adressService.getAllAdressesForPartnerId(id);
        List<AdressDTO> dtolist =  new ArrayList<>();

        for (Adress adress: adresslist){
            dtolist.add(AdressDTO.builder().id(adress.getId())
                    .postleitzahl(adress.getPostleitzahl())
                    .ort(adress.getOrt())
                    .strasse(adress.getStrasse())
                    .hausnummer(adress.getHausnummer())
                    .partnerid(adress.getPartner().getId())
                    .build());
        }
        model.addAttribute("adresses", dtolist);
        model.addAttribute("partnerId", id);
        return "list-adresses";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(@RequestParam("partnerId") int id, Model model){
        AdressDTO dto = new AdressDTO();
        dto.setPartnerid(id);
        model.addAttribute("adressDTO", dto);
        return "partner/adress-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("adressId") int id, Model model){
        Adress adress = adressService.findById(id);
        model.addAttribute("adressDTO", AdressDTO.builder()
                        .id(adress.getId())
                        .postleitzahl(adress.getPostleitzahl())
                        .ort(adress.getOrt())
                        .strasse(adress.getStrasse())
                        .hausnummer(adress.getHausnummer())
                        .partnerid(adress.getPartner().getId())
                        .build());
        return "partner/adress-form";
    }

    @PostMapping("/save")
    public  String saveAdresse(@ModelAttribute("adressDTO") AdressDTO adressDTO){
        Adress adress = Adress.builder()
                .id(adressDTO.getId())
                .postleitzahl(adressDTO.getPostleitzahl())
                .ort(adressDTO.getOrt())
                .strasse(adressDTO.getStrasse())
                .hausnummer(adressDTO.getHausnummer())
                .partner(partnerService.findById(adressDTO.getPartnerid()))
                .build();
        adressService.save(adress);

        return "redirect:/partner/adress/list?partnerId="+adress.getPartner().getId();
    }
}
