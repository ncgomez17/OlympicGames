package com.juegosolimpicos.services;

import com.juegosolimpicos.OlympicGamesApplicationTest;
import com.juegosolimpicos.openapi.model.CityDto;
import com.juegosolimpicos.openapi.model.CountryDto;
import com.juegosolimpicos.services.impl.CityServiceImpl;
import com.juegosolimpicos.exceptions.BaseException;
import com.juegosolimpicos.exceptions.ConflictException;
import com.juegosolimpicos.exceptions.EntityNotFoundException;
import com.juegosolimpicos.exceptions.FailedDependencyException;
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
@Tag("IntegrationTestsCityService")
public class CityServiceTest {

    @Autowired
    private CityServiceImpl cityService;

    private static final Logger log = LoggerFactory.getLogger(OlympicGamesApplicationTest.class);

    @Test
    public void testCityRepositoryNotNull() {
        assertNotNull(this.cityService);
    }

    @Test
    public void testCityGetCities() {
        log.info("-------Testing TestCityGetCities --------");
        List<CityDto> cities = cityService.getCities();
        assertEquals(12,cities.size());
    }

    @Test
    public void testCitySave() throws BaseException {
        log.info("-------Testing TestCitySave --------");
        CountryDto country = new CountryDto();
        country.setId(2);

        CityDto entity = new CityDto();
        entity.setName("FAFE");
        entity.setValue(null);
        entity.setCountry(country);

        CityDto city = this.cityService.save(entity);
        assertEquals(13, city.getId().intValue());
        assertEquals("FAFE", city.getName());
        assertEquals(null, city.getValue());
        assertEquals(2, city.getCountry().getId().intValue());

    }

    @Test(expected = ConflictException.class)
    public void testCitySaveExists() throws BaseException{
        log.info("-------Testing TestCitySaveExists --------");
        CountryDto country = new CountryDto();
        country.setId(2);

        CityDto entity = new CityDto();
        entity.setName("LISBOA");
        entity.setValue(134);
        entity.setCountry(country);

        this.cityService.save(entity);
    }

    @Test()
    public void testCityUpdate() throws BaseException{
        log.info("-------Testing TestCityUpdate --------");
        CountryDto country = new CountryDto();
        country.setId(2);

        CityDto entity = new CityDto();
        entity.setId(4);
        entity.setName("LISBOA");
        entity.setValue(200);
        entity.setCountry(country);

        CityDto city = this.cityService.save(entity);
        assertEquals(4, city.getId().intValue());
        assertEquals("LISBOA", city.getName());
        assertEquals(200, city.getValue().intValue());
        assertEquals(2, city.getCountry().getId().intValue());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCityUpdateNotExistsId() throws BaseException {
        log.info("-------Testing TestCityUpdateNotExistsId --------");
        CountryDto country = new CountryDto();
        country.setId(1);

        CityDto entity = new CityDto();
        entity.setId(13);
        entity.setName("Sevilla");
        entity.setValue(200);
        entity.setCountry(country);

        this.cityService.save(entity);
    }
    @Test()
    public void testCityGetCountHeadquarters() {
        log.info("-------Testing TestCityGetCountHeadquarters --------");

        assertEquals(1,this.cityService.getCountHeadquarters(3).intValue());
    }

    @Test()
    public void testCityGetCountHeadquartersEmpty() {
        log.info("-------Testing TestCityGetCountHeadquartersEmpty --------");

        assertEquals(0,this.cityService.getCountHeadquarters(2).intValue());
    }

    @Test()
    public void testCityDelete() throws BaseException{
        log.info("-------testCityDelete --------");
        this.cityService.deleteById(5);
        assertEquals(11, this.cityService.getCities().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCityDeleteIdNotExists() throws BaseException {
        log.info("-------testCityDeleteIdNotExists --------");
        this.cityService.deleteById(13);
    }

    @Test(expected = NullPointerException.class)
    public void testCityDeleteIdNull() throws BaseException {
        log.info("-------testCityDeleteIdNull --------");
        this.cityService.deleteById(null);
    }
    @Test(expected = FailedDependencyException.class)
    public void testCityDeleteReferentialViolation() throws BaseException {
        log.info("------- testCityDeleteReferentialViolation --------");
        this.cityService.deleteById(3);
    }

    @Test()
    public void testCityGet() throws BaseException {
        log.info("-------testCityGet --------");
        CityDto city = this.cityService.getById(1);
        assertEquals(1, city.getId().intValue());
        assertEquals("LA CORUÑA", city.getName());
        assertEquals(93, city.getValue().intValue());
        assertEquals(1, city.getCountry().getId().intValue());
    }

    @Test(expected = NullPointerException.class)
    public void testCityGetIdNull() throws BaseException {
        log.info("------- TestCityGetIdNull --------");
        this.cityService.getById(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCityGetIdNotExists() throws BaseException {
        log.info("-------testCityGetIdNotExists --------");
        CityDto city = this.cityService.getById(14);
    }

}
