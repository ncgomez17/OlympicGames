package com.juegosolimpicos.services.predicates;

import com.juegosolimpicos.model.entities.sql.QCountryEntity;
import com.juegosolimpicos.openapi.model.FindCountryRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class CountryPredicates {
    public static  BooleanBuilder findCountryList(FindCountryRequestDto countryRequestDto){
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression predicateName = null;
        BooleanExpression predicateId = null;
        BooleanExpression predicateCityId = null;

        QCountryEntity country = QCountryEntity.countryEntity;

        if(Objects.nonNull(countryRequestDto.getName())){
            predicateName = country.name.contains(countryRequestDto.getName());
        }
        if(Objects.nonNull(countryRequestDto.getId())){
            predicateId = country.id.stringValue().contains(countryRequestDto.getId().toString());
        }
        if(Objects.nonNull(countryRequestDto.getCityId())){
            predicateCityId = country.cities.any().id.stringValue().contains(countryRequestDto.getCityId().toString());
        }
        return builder.orAllOf(predicateName,predicateId, predicateCityId);
    }
}
