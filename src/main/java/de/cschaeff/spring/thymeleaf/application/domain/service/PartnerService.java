package de.cschaeff.spring.thymeleaf.application.domain.service;

import de.cschaeff.spring.thymeleaf.application.adapter.db.PartnerRepository;
import de.cschaeff.spring.thymeleaf.application.adapter.db.entity.PartnerEntity;
import de.cschaeff.spring.thymeleaf.application.domain.bo.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    public PartnerService(){
    }

    public List<Partner> findAll() {

        List<PartnerEntity> partnerentitylist = partnerRepository.findAll();
        List<Partner> partnerliste = new ArrayList<>();
        for (PartnerEntity entity: partnerentitylist){
            partnerliste.add(Partner.builder().id(entity.getId())
                    .name(entity.getName())
                    .vorname(entity.getVorname())
                    .email(entity.getEmail())
                    .build());
        }
        return partnerliste;
    }

    public Partner findById(int id) {
        Optional<PartnerEntity> result = partnerRepository.findById(id);
        if (result.isPresent()) {
            PartnerEntity entity = result.get();
            return Partner.builder().id(entity.getId())
                    .name(entity.getName())
                    .vorname(entity.getVorname())
                    .email(entity.getEmail())
                    .build();
        }
        else {
            throw new RuntimeException("Did not find partner id - " + id);
        }
    }

    public void save(Partner partner) {
        partnerRepository.save(PartnerEntity.builder().id(partner.getId())
                .name(partner.getName())
                .vorname(partner.getVorname())
                .email(partner.getEmail())
                .build());
    }

    public void deleteById(int id) {
        partnerRepository.deleteById(id);
    }
}
