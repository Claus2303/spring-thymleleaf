package de.cschaeff.spring.thymeleaf.application.adapter.db;

import de.cschaeff.spring.thymeleaf.application.ThymeleafApplication;
import de.cschaeff.spring.thymeleaf.application.adapter.db.entity.AdressEntity;
import de.cschaeff.spring.thymeleaf.application.adapter.db.entity.PartnerEntity;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= ThymeleafApplication.class)
class AdressRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdressRepository adressRepository;
    @Autowired
    PartnerRepository partnerRepository;

    @Test
    @Transactional
    public void saveAndReadAdress(){

        PartnerEntity partnerEntity = new PartnerEntity("Schaeffner", "Claus", "claus@schaeffner.de");

        logger.info("Partner -> {}", partnerEntity);

        AdressEntity adressEntity = new AdressEntity(91301, "Forchheim","Am Kressenacker", "9b" );

        partnerEntity.addAdresse(adressEntity);
        adressEntity.setPartner(partnerEntity);
        logger.info("Partner -> {}", partnerEntity);

        partnerEntity = partnerRepository.save(partnerEntity);
        adressEntity = adressRepository.save(adressEntity);
        logger.info("Partner -> {}", partnerEntity);
    }

}