package de.cschaeff.spring.thymeleaf.application.domain.bo;

import de.cschaeff.spring.thymeleaf.application.adapter.db.entity.PartnerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@Builder
public class Adress {

    private int id;

    private int postleitzahl;

    private String ort;

    private String strasse;

    private String hausnummer;

    private Partner partner;
}
