package com.juegosolimpicos.services;

import com.juegosolimpicos.openapi.model.CityDto;
import com.juegosolimpicos.openapi.model.CountryDto;
import com.juegosolimpicos.openapi.model.FindCountryRequestDto;
import com.juegosolimpicos.openapi.model.FindCountryResponseDto;
import com.juegosolimpicos.exceptions.BaseException;

import java.util.List;

public interface ICountryService {
    List<CountryDto> getCountries();

    List<CityDto> getCitiesOfCountry(Integer id);

    CountryDto save(CountryDto c) throws BaseException;

    void deleteById(Integer id) throws BaseException;

    CountryDto getById(Integer id) throws BaseException;

    FindCountryResponseDto findCountryList(FindCountryRequestDto countryRequestDto, Integer offSet, Integer limitParam,
                                                  String sortDirection, String sortProperty) ;
}
