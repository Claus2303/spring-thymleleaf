package de.cschaeff.spring.thymeleaf.application.adapter.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartnerEntity {

    @Id
    @GeneratedValue
    private int id;

    private String vorname;

    private String name;

    private String email;
}
