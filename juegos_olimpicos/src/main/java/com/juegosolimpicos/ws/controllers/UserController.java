package com.juegosolimpicos.ws.controllers;

import com.juegosolimpicos.exceptions.BaseException;
import com.juegosolimpicos.openapi.api.UserApi;
import com.juegosolimpicos.openapi.model.SessionDto;
import com.juegosolimpicos.openapi.model.UserDto;
import com.juegosolimpicos.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController implements UserApi {

    @Autowired
    private IUserService userService;


    @Override
    public ResponseEntity<SessionDto> authenticate(UserDto userDto) throws BaseException {
        SessionDto authenticate = userService.authenticate(userDto);
        return new ResponseEntity<>(authenticate, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SessionDto> register(UserDto userDto) throws BaseException {
        SessionDto register = userService.register(userDto);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> updatePassword(String password) throws BaseException {
        UserDto register = userService.updatePassword(password);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> getIsAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Boolean isAdmin = false;
        if(Objects.nonNull(auth) && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
            isAdmin = true;
        }
        return new ResponseEntity<>(isAdmin,HttpStatus.OK);
    }




}
