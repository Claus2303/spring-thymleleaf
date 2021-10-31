package de.cschaeff.spring.thymeleaf.application.adapter.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Partner")
public class PartnerEntity {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
    private String vorname;
    @Column(nullable = false)
    private String name;

    private String email;
    @OneToMany(mappedBy = "partner")
    private List<AdressEntity> adressen = new ArrayList<>();


    protected PartnerEntity(){}

    public PartnerEntity(String name, String vorname, String email){
        this.name = name;
        this.vorname = vorname;
        this.email = email;
    }
    public void addAdresse(AdressEntity adressEntity){
        this.adressen.add(adressEntity);
    }

}
