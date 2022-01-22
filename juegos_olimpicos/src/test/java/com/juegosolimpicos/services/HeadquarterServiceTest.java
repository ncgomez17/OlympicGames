package com.juegosolimpicos.services;

import com.juegosolimpicos.OlympicGamesApplicationTest;
import com.juegosolimpicos.openapi.model.*;
import com.juegosolimpicos.services.impl.HeadquarterServiceImpl;
import com.juegosolimpicos.exceptions.BaseException;
import com.juegosolimpicos.exceptions.ConflictException;
import com.juegosolimpicos.exceptions.EntityNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@ContextConfiguration(
        loader = AnnotationConfigContextLoader.class)
@Sql(config = @SqlConfig(encoding = "utf-8"),scripts = {"classpath:juegosolimpicos_ddl.sql"})
@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Tag("IntegrationTestsHeadquarterService")
public class HeadquarterServiceTest {

    @Autowired
    private HeadquarterServiceImpl headquarterService;

    private static final Logger log = LoggerFactory.getLogger(OlympicGamesApplicationTest.class);


    @Test
    public void TestHeadquarterRepositoryNotNull() {
        assertNotNull(this.headquarterService);
    }

    @Test
    public void testHeadquarterGetHeadquarters() {
        log.info("-------Testing testHeadquarterGetHeadquarters --------");
        List<HeadquarterDto> headquarters = this.headquarterService.getHeadquarters();
        assertEquals(6,headquarters.size());
    }

    @Test
    public void testGetOlympicGames(){
        log.info("-------Testing testGetOlympicGames --------");
        List<OlympicGamesDto> listOlympicGames = this.headquarterService.getOlympicGames();
        assertNotNull(listOlympicGames);
        assertEquals(12,listOlympicGames.size());

    }

    @Test
    public void testHeadquarterSave() throws BaseException {
        log.info("-------Testing testHeadquarterSave --------");
        CityDto city = new CityDto();
        city.setId(2);
        TypeJjooDto type = new TypeJjooDto();
        type.setId(1);

        HeadquarterDto entity = new HeadquarterDto();
        entity.getId().setYear(1908);
        entity.setCity(city);
        entity.getId().setType(type);

        HeadquarterDto headquarter = this.headquarterService.save(entity);
        assertEquals(1908, headquarter.getId().getYear().intValue());
        assertEquals(2, headquarter.getCity().getId().intValue());
        assertEquals(1, headquarter.getId().getType().getId().intValue());

    }

    @Test(expected = ConflictException.class)
    public void testHeadquarterSaveExists() throws BaseException {
        log.info("-------Testing testHeadquarterSaveExists --------");
        CityDto city = new CityDto();
        city.setId(3);
        TypeJjooDto type = new TypeJjooDto();
        type.setId(2);

        HeadquarterDto entity = new HeadquarterDto();
        entity.getId().setYear(1992);
        entity.setCity(city);
        entity.getId().setType(type);

        this.headquarterService.save(entity);
    }

    @Test()
    public void testHeadquarterUpdate() throws BaseException {
        log.info("-------Testing testHeadquarterUpdate --------");
        CityDto city = new CityDto();
        city.setId(3);
        TypeJjooDto type = new TypeJjooDto();
        type.setId(1);

        HeadquarterDto entity = new HeadquarterDto();
        entity.getId().setYear(1992);
        entity.setCity(city);
        entity.getId().setType(type);

        this.headquarterService.save(entity);
    }

    @Test()
    public void testHeadquarterDelete() throws BaseException {
        log.info("-------testHeadquarterDelete --------");
        this.headquarterService.deleteById(1992,2);
        assertEquals(5, this.headquarterService.getHeadquarters().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testHeadquarterDeleteIdNotExists() throws BaseException {
        log.info("------- testHeadquarterDeleteIdNotExists --------");
        this.headquarterService.deleteById(1940,2);
    }

    @Test(expected = NullPointerException.class)
    public void testHeadquarterDeleteIdNull() throws BaseException {
        log.info("-------testHeadquarterDeleteIdNull --------");
        this.headquarterService.deleteById(null,2);
    }

    @Test()
    public void testHeadquarterGet() throws BaseException {
        log.info("-------testHeadquarterGet --------");
        HeadquarterDto headquarter = this.headquarterService.getById(1992,2);
        assertEquals(1992, headquarter.getId().getYear().intValue());
        assertEquals(3, headquarter.getCity().getId().intValue());
        assertEquals(2, headquarter.getId().getType().getId().intValue());
    }

    @Test(expected = NullPointerException.class)
    public void testHeadquarterGetIdNull() throws BaseException {
        log.info("------- testHeadquarterGetIdNull --------");
        this.headquarterService.getById(null,3);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testHeadquarterGetIdNotExists() throws BaseException {
        log.info("-------testHeadquarterGetIdNotExists --------");
        HeadquarterDto headquarterDto= this.headquarterService.getById(1000,3);
    }

}
