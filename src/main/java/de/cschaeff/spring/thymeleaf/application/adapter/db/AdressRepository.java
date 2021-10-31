package de.cschaeff.spring.thymeleaf.application.adapter.db;

import de.cschaeff.spring.thymeleaf.application.adapter.db.entity.AdressEntity;
import de.cschaeff.spring.thymeleaf.application.adapter.db.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdressRepository extends JpaRepository<AdressEntity, Integer> {

    public List<AdressEntity> findByPartner(PartnerEntity partnerEntity);
}
