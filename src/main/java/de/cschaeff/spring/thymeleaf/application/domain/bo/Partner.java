package de.cschaeff.spring.thymeleaf.application.domain.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Partner {

    private int id;

    private String name;
    private String vorname;
    private String email;
}
