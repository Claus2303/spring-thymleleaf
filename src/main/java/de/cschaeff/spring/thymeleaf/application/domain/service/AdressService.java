package de.cschaeff.spring.thymeleaf.application.domain.service;

import de.cschaeff.spring.thymeleaf.application.adapter.db.AdressRepository;
import de.cschaeff.spring.thymeleaf.application.adapter.db.PartnerRepository;
import de.cschaeff.spring.thymeleaf.application.adapter.db.entity.AdressEntity;
import de.cschaeff.spring.thymeleaf.application.adapter.db.entity.PartnerEntity;
import de.cschaeff.spring.thymeleaf.application.domain.bo.Adress;
import de.cschaeff.spring.thymeleaf.application.domain.bo.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdressService {

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    public AdressService(){
    }

    public List<Adress> getAllAdressesForPartnerId(int paid){
        PartnerEntity partnerEntity = partnerRepository.getById(paid);

       List<AdressEntity> adressEntityList = adressRepository.findByPartner(partnerEntity);
       List<Adress> adresses = new ArrayList<>();
       for (AdressEntity entity: adressEntityList){
           adresses.add(Adress.builder().id(entity.getId())
                   .postleitzahl(entity.getPostleitzahl())
                   .ort(entity.getOrt())
                   .strasse(entity.getStrasse())
                   .hausnummer(entity.getHausnummer())
                   .partner(getPartnerBo(entity.getPartner()))
                   .build());
       }
       return adresses;
    }

    public Adress findById(int id) {
        Optional<AdressEntity> result = adressRepository.findById(id);
        if (result.isPresent()) {
            AdressEntity entity = result.get();
            return Adress.builder().id(entity.getId())
                    .postleitzahl(entity.getPostleitzahl())
                    .ort(entity.getOrt())
                    .strasse(entity.getStrasse())
                    .hausnummer(entity.getHausnummer())
                    .partner(getPartnerBo(entity.getPartner()))
                    .build();
        }
        else {
            throw new RuntimeException("Did not find partner id - " + id);
        }
    }

    private Partner getPartnerBo(PartnerEntity partnerEntity){
        return Partner.builder()
                .id(partnerEntity.getId())
                .name(partnerEntity.getName())
                .vorname(partnerEntity.getVorname())
                .email(partnerEntity.getEmail())
                .build();
    }

    private PartnerEntity getPartnerEntity(Partner partner){
        PartnerEntity partnerEntity = new PartnerEntity(partner.getName(), partner.getVorname(), partner.getEmail());
        partnerEntity.setId(partner.getId());
        return partnerEntity;
    }

    public void save(Adress adress) {

        AdressEntity adressEntity = new AdressEntity(adress.getPostleitzahl(), adress.getOrt(), adress.getStrasse(), adress.getHausnummer());
        adressEntity.setId(adress.getId());
        adressEntity.setPartner(getPartnerEntity(adress.getPartner()));
        adressRepository.save(adressEntity);
    }

}
