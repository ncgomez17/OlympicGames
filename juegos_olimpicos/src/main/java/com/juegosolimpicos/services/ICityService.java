package com.juegosolimpicos.services;


import com.juegosolimpicos.openapi.model.*;
import com.juegosolimpicos.exceptions.BaseException;

import java.util.List;


public interface ICityService {

    List<CityDto> getCities();

    CityDto save(CityDto c) throws BaseException;

    Integer getCountHeadquarters(Integer id);

    void deleteById(Integer id) throws BaseException;

    CityDto getById(Integer id) throws BaseException;

    FindCityResponseDto findCityList(FindCityRequestDto countryRequestDto, Integer offSet, Integer limitParam,
                                     String sortDirection, String sortProperty);

}
