package com.juegosolimpicos.services;


import com.juegosolimpicos.openapi.model.*;
import com.juegosolimpicos.exceptions.BaseException;

import java.util.List;

public interface IHeadquarterService {
    List<HeadquarterDto> getHeadquarters();

    List<OlympicGamesDto> getOlympicGames();

    HeadquarterDto save(HeadquarterDto c) throws BaseException;

    void deleteById(Integer id,Integer year) throws BaseException;

    HeadquarterDto getById(Integer id,Integer year) throws BaseException;

    FindHeadquarterResponseDto findHeadquarterList(FindHeadquarterRequestDto headquarterRequestDto, Integer offSet, Integer limitParam,
                                           String sortDirection, String sortProperty);
    FindOlympicGamesResponseDto findOlympicGamesList(Integer offSet, Integer limitParam,
                                                            String sortDirection, String sortProperty);
}
