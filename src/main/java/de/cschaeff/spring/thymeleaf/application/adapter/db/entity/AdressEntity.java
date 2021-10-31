package de.cschaeff.spring.thymeleaf.application.adapter.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Adresse")
public class AdressEntity {

    @Id
    @GeneratedValue
    private int id;

    private int postleitzahl;

    private String ort;

    private String strasse;

    private String hausnummer;
    @ManyToOne
    private PartnerEntity partner;

    protected AdressEntity(){

    }

    public AdressEntity(int postleitzahl, String ort, String strasse, String hausnummer){
        this.postleitzahl = postleitzahl;
        this.ort = ort;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
    }

    @Override
    public String toString() {
        return "AdressEntity{" +
                "id=" + id +
                ", postleitzahl=" + postleitzahl +
                ", ort='" + ort + '\'' +
                ", strasse='" + strasse + '\'' +
                ", hausnummer='" + hausnummer + '\'' +
                ", partner=" + partner.getId() +
                '}';
    }
}
