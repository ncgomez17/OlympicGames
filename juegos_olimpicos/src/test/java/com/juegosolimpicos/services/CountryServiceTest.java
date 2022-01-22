package com.juegosolimpicos.services;

import com.juegosolimpicos.OlympicGamesApplicationTest;
import com.juegosolimpicos.services.impl.CountryServiceImpl;
import com.juegosolimpicos.openapi.model.CountryDto;
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
@Tag("IntegrationTestsCountryService")
public class CountryServiceTest {

    @Autowired
    private CountryServiceImpl countryService;

    private static final Logger log = LoggerFactory.getLogger(OlympicGamesApplicationTest.class);

    @Test
    public void testCountryRepositoryNotNull() {
        log.info("-------Testing TestCountryRepositoryNotNull --------");
        assertNotNull(this.countryService);
    }

    @Test
    public void testCountryGetCountries() {
        log.info("-------Testing TestCountryGetCountries --------");
        List<CountryDto> countries = this.countryService.getCountries();
        assertEquals(5, countries.size());
    }

    @Test
    public void testCountryGetCities() {
        log.info("-------Testing TestCountryGetCities --------");
        int numCities = this.countryService.getCitiesOfCountry(1).size();
        assertEquals(3, numCities);
    }

    @Test
    public void testCountrySave() throws BaseException {
        log.info("-------Testing TestCountrySave --------");
        CountryDto country1 = new CountryDto();
        country1.setCode("AR");
        country1.setName("Argentina");
        country1.setValue(100);

        CountryDto countrydto = this.countryService.save(country1);
        assertEquals("Argentina", countrydto.getName());
        assertEquals("AR", countrydto.getCode());
        assertEquals(100, countrydto.getValue().intValue());

    }

    @Test(expected = ConflictException.class)
    public void testCountrySaveExists() throws BaseException {
        log.info("-------Testing TestCountrySaveExists --------");
        CountryDto country1 = new CountryDto();
        country1.setCode("ES");
        country1.setName("ESPAÑA");
        country1.setValue(100);

        this.countryService.save(country1);

    }

    @Test()
    public void testCountryUpdate() throws BaseException {
        log.info("-------Testing TestCountrySaveUpdate --------");
        CountryDto country1 = new CountryDto();
        country1.setId(1);
        country1.setCode("ES");
        country1.setName("ESPAÑA");
        country1.setValue(300);

        CountryDto countryDto = this.countryService.save(country1);
        assertEquals("ESPAÑA", countryDto.getName());
        assertEquals("ES", countryDto.getCode());
        assertEquals(300, countryDto.getValue().intValue());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCountryUpdateNotExistsId() throws BaseException {
        log.info("-------Testing TestCountryUpdateNotExistsId --------");
        CountryDto country1 = new CountryDto();
        country1.setId(6);
        country1.setCode("AR");
        country1.setName("ARGENTINA");
        country1.setValue(300);

        this.countryService.save(country1);
    }

    @Test()
    public void testCountryDelete() throws BaseException {
        log.info("-------TestCountryDelete --------");
        this.countryService.deleteById(5);
        assertEquals(4, this.countryService.getCountries().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCountryDeleteIdNotExists() throws BaseException {
        log.info("-------TestCountryDeleteIdNotExists --------");
        this.countryService.deleteById(6);
    }

    @Test(expected = NullPointerException.class)
    public void testCountryDeleteIdNull() throws BaseException {
        log.info("-------TestCountryDeleteIdNull --------");
        this.countryService.deleteById(null);
    }
    @Test(expected = FailedDependencyException.class)
    public void testCountryDeleteReferentialViolation() throws BaseException {
        log.info("------- TestCountryDeleteReferentialViolation --------");
        this.countryService.deleteById(1);
    }

    @Test()
    public void testCountryGet() throws BaseException {
        log.info("-------TestCountryGet --------");
        CountryDto country = this.countryService.getById(1);
        assertEquals(1, country.getId().intValue());
        assertEquals("ESPAÑA", country.getName());
        assertEquals("ES", country.getCode());
        assertEquals(100, country.getValue().intValue());
    }

    @Test(expected = NullPointerException.class)
    public void testCountryGetIdNull() throws BaseException{
        log.info("------- TestCountryGetIdNull --------");
        this.countryService.getById(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testCountryGetIdNotExists() throws BaseException {
        log.info("-------testCountryGetIdNotExists --------");
        CountryDto countryDto= this.countryService.getById(6);
    }

}
