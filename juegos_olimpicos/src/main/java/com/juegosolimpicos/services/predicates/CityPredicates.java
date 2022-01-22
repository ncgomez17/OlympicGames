package com.juegosolimpicos.services.predicates;

import com.juegosolimpicos.model.entities.sql.QCityEntity;
import com.juegosolimpicos.openapi.model.FindCityRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class CityPredicates {

    public static Predicate findCityList(FindCityRequestDto cityRequestDto){
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression predicateName = null;
        BooleanExpression predicateId = null;

        QCityEntity city = QCityEntity.cityEntity;

        if(Objects.nonNull(cityRequestDto.getName())){
            predicateName = city.name.contains(cityRequestDto.getName());
        }
        if(Objects.nonNull(cityRequestDto.getId())){
            predicateId = city.id.stringValue().contains(cityRequestDto.getId().toString());
        }

        return builder.orAllOf(predicateName,predicateId);
    }
}
