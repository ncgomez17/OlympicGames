package com.juegosolimpicos.ws.controllers;

import com.juegosolimpicos.exceptions.BaseException;
import com.juegosolimpicos.openapi.api.HeadquarterApi;
import com.juegosolimpicos.openapi.model.*;
import com.juegosolimpicos.services.IHeadquarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class HeadquarterController implements HeadquarterApi {

    @Autowired
    private IHeadquarterService headquarterService;

    @Override
    public ResponseEntity<HeadquarterDto> createHeadquarter(@Valid final HeadquarterDto headquarterDto) throws BaseException {
        final HeadquarterDto response = this.headquarterService.save(headquarterDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> deleteHeadquarter(@Valid final Integer year, @Min(1) @Valid final Integer typeId) throws BaseException{
        this.headquarterService.deleteById(year,typeId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @Override
    public ResponseEntity<HeadquarterDto> getHeadquarterById(@Valid final Integer year,@Min(1) @Valid final Integer typeId) throws BaseException{
        final HeadquarterDto response = this.headquarterService.getById(year,typeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HeadquarterDto>> getHeadquarters(){
        final List<HeadquarterDto> response = this.headquarterService.getHeadquarters();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<OlympicGamesDto>> getOlympicGames(){
        final List<OlympicGamesDto> response = this.headquarterService.getOlympicGames();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HeadquarterDto> updateHeadquarter(@Valid final HeadquarterDto headquarterDto) throws BaseException{
        final HeadquarterDto response = this.headquarterService.save(headquarterDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FindHeadquarterResponseDto> findHeadquarterList(FindHeadquarterRequestDto filter, Integer offsetParam,
                                                            Integer limit, String sortDirection, String sortProperty) {
        final FindHeadquarterResponseDto response = this.headquarterService.findHeadquarterList(filter, offsetParam, limit, sortDirection, sortProperty);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FindOlympicGamesResponseDto> findOlympicGamesList(Integer offsetParam,
                                                                            Integer limit, String sortDirection, String sortProperty){
        final FindOlympicGamesResponseDto response = this.headquarterService.findOlympicGamesList(offsetParam, limit, sortDirection, sortProperty);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
