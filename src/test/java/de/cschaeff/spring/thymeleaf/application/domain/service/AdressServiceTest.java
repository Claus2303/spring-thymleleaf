package de.cschaeff.spring.thymeleaf.application.domain.service;

import de.cschaeff.spring.thymeleaf.application.ThymeleafApplication;
import de.cschaeff.spring.thymeleaf.application.domain.bo.Adress;
import de.cschaeff.spring.thymeleaf.application.domain.bo.Partner;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= ThymeleafApplication.class)
class AdressServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AdressService adressService;

    @Autowired
    PartnerService partnerService;

    @Test
    public void verifyAdressForPartner(){
        List<Adress> adresslist = adressService.getAllAdressesForPartnerId(10001);
        logger.info("Adresses für Partner ->{}", adresslist);
    }

}