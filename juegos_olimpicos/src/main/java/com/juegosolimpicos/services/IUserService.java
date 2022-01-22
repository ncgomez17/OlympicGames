package com.juegosolimpicos.services;

import com.juegosolimpicos.openapi.model.SessionDto;
import com.juegosolimpicos.openapi.model.UserDto;
import com.juegosolimpicos.exceptions.BaseException;

import java.util.List;

public interface IUserService {

    List<UserDto> getUsers();

    SessionDto register(UserDto c) throws BaseException;

    UserDto updatePassword(String password) throws BaseException;

    UserDto getByName(String name) throws BaseException;

    public SessionDto authenticate(UserDto userDto) throws BaseException;

}
