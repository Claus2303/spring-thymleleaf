package de.cschaeff.spring.thymeleaf.application.adapter.db;

import de.cschaeff.spring.thymeleaf.application.adapter.db.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository extends JpaRepository<PartnerEntity, Integer> {

    // search by name
    public List<PartnerEntity> findByVornameContainsOrNameContainsAllIgnoreCase(String vorname, String nachname);
}
