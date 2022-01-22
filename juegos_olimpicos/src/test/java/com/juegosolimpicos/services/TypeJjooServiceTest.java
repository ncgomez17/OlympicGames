package com.juegosolimpicos.services;

import com.juegosolimpicos.OlympicGamesApplication;
import com.juegosolimpicos.openapi.model.TypeJjooDto;
import com.juegosolimpicos.services.impl.TypeJjooServiceImpl;
import com.juegosolimpicos.exceptions.BaseException;
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
@Tag("IntegrationTestsTypeJjooService")
public class TypeJjooServiceTest {

    @Autowired
    private TypeJjooServiceImpl typeJjooService;

    private static final Logger log = LoggerFactory.getLogger(OlympicGamesApplication.class);

    @Test
    public void TestTypeJjooRepositoryNotNull() {
        assertNotNull(this.typeJjooService);
    }

    @Test
    public void testTypeJjooGetTypesJjoo() {
        log.info("-------Testing testTypeJjooGetTypesJjoo --------");
        List<TypeJjooDto> typesJjoo = this.typeJjooService.getTypesJjoo();
        assertEquals(3,typesJjoo.size());
    }

    @Test()
    public void testTypeJjooDelete() throws BaseException {
        log.info("-------testTypeJjooDelete --------");
        this.typeJjooService.deleteById(3);
        assertEquals(2, this.typeJjooService.getTypesJjoo().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testTypeJjooDeleteIdNotExists() throws BaseException {
        log.info("------- testTypeJjooDeleteIdNotExists --------");
        this.typeJjooService.deleteById(5);
    }

    @Test(expected = NullPointerException.class)
    public void testTypeJjooDeleteIdNull() throws BaseException {
        log.info("-------testTypeJjooDeleteIdNull --------");
        this.typeJjooService.deleteById(null);
    }

    @Test()
    public void testTypeJjooGet() throws BaseException{
        log.info("-------testTypeJjooGet --------");
        TypeJjooDto typeJjoo = this.typeJjooService.getById(1);
        assertEquals(1, typeJjoo.getId().intValue());
        assertEquals("INVIERNO", typeJjoo.getDescription());
    }

    @Test(expected = NullPointerException.class)
    public void testTypeJjooGetIdNull() throws BaseException{
        log.info("------- testTypeJjooGetIdNull --------");
        this.typeJjooService.getById(null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testTypeJjooGetIdNotExists() throws BaseException {
        log.info("-------testTypeJjooGetIdNotExists --------");
        TypeJjooDto typeJjooDto = this.typeJjooService.getById(4);
    }
}
