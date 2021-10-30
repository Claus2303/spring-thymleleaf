package de.cschaeff.spring.thymeleaf.application.adapter.web;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Partner {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
