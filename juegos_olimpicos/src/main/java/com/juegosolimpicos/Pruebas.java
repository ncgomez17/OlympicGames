package com.juegosolimpicos;

import com.juegosolimpicos.exceptions.BaseException;
import com.juegosolimpicos.services.IUserService;
import com.juegosolimpicos.services.impl.CityServiceImpl;
import com.juegosolimpicos.services.impl.CountryServiceImpl;
import com.juegosolimpicos.services.impl.HeadquarterServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Pruebas implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(OlympicGamesApplication.class);

    @Autowired
    private CountryServiceImpl country;

    @Autowired
    private CityServiceImpl city;

    @Autowired
    private IUserService userService;

    @Autowired
    private HeadquarterServiceImpl headquarter;

    @Override
    public void run(String... args)  throws BaseException {
//        UserDto userDto = new UserDto();
//        userDto.setName("ngomez17");
//        userDto.setPassword("nicolas");
//        log.info(userService.authenticate(userDto).toString());
    }

}
