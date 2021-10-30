package de.cschaeff.spring.thymeleaf.application.adapter.db;

import de.cschaeff.spring.thymeleaf.application.adapter.db.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<PartnerEntity, Integer> {
}
