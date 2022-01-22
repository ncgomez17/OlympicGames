package com.juegosolimpicos.ws.controllers;

import com.juegosolimpicos.openapi.api.CountryApi;
import com.juegosolimpicos.openapi.model.CityDto;
import com.juegosolimpicos.openapi.model.CountryDto;
import com.juegosolimpicos.openapi.model.FindCountryRequestDto;
import com.juegosolimpicos.openapi.model.FindCountryResponseDto;
import com.juegosolimpicos.services.ICountryService;
import com.juegosolimpicos.exceptions.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class CountryController implements CountryApi {

    @Autowired
    private ICountryService countryService;

    @Override
    public ResponseEntity<CountryDto> createCountry(@Valid final CountryDto countryDto) throws BaseException {
        final CountryDto response = this.countryService.save(countryDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> deleteCountry(@Min(1) @Valid final Integer countryId) throws BaseException{
        this.countryService.deleteById(countryId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<CountryDto>> getCountries(){
        final List<CountryDto> response = this.countryService.getCountries();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CountryDto> getCountryById(@Min(1) @Valid final Integer countryId) throws BaseException{
        final CountryDto response = this.countryService.getById(countryId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CityDto>> getCountryCities(@Min(1) @Valid final Integer countryId){
        final List<CityDto> response = this.countryService.getCitiesOfCountry(countryId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CountryDto> updateCountry(@Valid final CountryDto countryDto) throws BaseException {
        final CountryDto response = this.countryService.save(countryDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<FindCountryResponseDto> findCountryList(FindCountryRequestDto filter, Integer offsetParam,
                                                                  Integer limit, String sortDirection, String sortProperty) {
        final FindCountryResponseDto response = this.countryService.findCountryList(filter, offsetParam, limit, sortDirection, sortProperty);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }





}
