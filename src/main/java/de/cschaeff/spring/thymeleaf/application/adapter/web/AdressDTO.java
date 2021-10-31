package de.cschaeff.spring.thymeleaf.application.adapter.web;

import de.cschaeff.spring.thymeleaf.application.domain.bo.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdressDTO {

    private int id;

    private int postleitzahl;

    private String ort;

    private String strasse;

    private String hausnummer;

    private int partnerid;
}
