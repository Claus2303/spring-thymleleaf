package de.cschaeff.spring.thymeleaf.application.adapter.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartnerDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
