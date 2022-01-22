package com.juegosolimpicos.services;


import com.juegosolimpicos.openapi.model.TypeJjooDto;
import com.juegosolimpicos.exceptions.BaseException;

import java.util.List;

public interface ITypeJjooService {

    List<TypeJjooDto> getTypesJjoo();

    void deleteById(Integer id) throws BaseException;

    TypeJjooDto getById(Integer id) throws BaseException;
}
