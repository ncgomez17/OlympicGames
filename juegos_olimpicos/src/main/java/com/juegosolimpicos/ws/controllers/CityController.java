package com.juegosolimpicos.ws.controllers;

import com.juegosolimpicos.openapi.api.CityApi;
import com.juegosolimpicos.openapi.model.CityDto;
import com.juegosolimpicos.openapi.model.FindCityRequestDto;
import com.juegosolimpicos.openapi.model.FindCityResponseDto;
import com.juegosolimpicos.services.ICityService;
import com.juegosolimpicos.exceptions.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class CityController implements CityApi {

    @Autowired
    private ICityService cityService;

    @Override
    public ResponseEntity<CityDto> createCity(@Valid final CityDto cityDto) throws BaseException {
        final CityDto response = this.cityService.save(cityDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> deleteCity(@Min(1) @Valid final Integer cityId) throws BaseException{
        this.cityService.deleteById(cityId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<CityDto>> getCities(){
        final List<CityDto> response = this.cityService.getCities();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityDto> getCityById(@Min(1) @Valid final Integer cityId) throws BaseException{
        final CityDto response = this.cityService.getById(cityId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getCountHeadquarters(@Min(1) @Valid final Integer cityId){
        final Integer response = this.cityService.getCountHeadquarters(cityId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CityDto> updateCity(@Valid final CityDto cityDto) throws BaseException{
        final CityDto response = this.cityService.save(cityDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FindCityResponseDto> findCityList(FindCityRequestDto filter, Integer offsetParam,
                                                               Integer limit, String sortDirection, String sortProperty) {
        final FindCityResponseDto response = this.cityService.findCityList(filter, offsetParam, limit, sortDirection, sortProperty);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
