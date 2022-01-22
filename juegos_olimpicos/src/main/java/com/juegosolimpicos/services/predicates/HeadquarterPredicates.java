package com.juegosolimpicos.services.predicates;

import com.juegosolimpicos.model.entities.sql.QHeadquarterEntity;
import com.juegosolimpicos.openapi.model.FindHeadquarterRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class HeadquarterPredicates {

    public static BooleanBuilder findHeadquarterList(FindHeadquarterRequestDto headquarterRequestDto){
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression predicateYear = null;
        BooleanExpression predicateCityName = null;

        QHeadquarterEntity headquarter = QHeadquarterEntity.headquarterEntity;
        if(Objects.nonNull(headquarterRequestDto.getCityName())){
            predicateCityName = headquarter.city.name.contains(headquarterRequestDto.getCityName());
        }
        if(Objects.nonNull(headquarterRequestDto.getYear())){
            predicateYear = headquarter.id.year.stringValue().contains(headquarterRequestDto.getYear().toString());
        }

        return builder.orAllOf(predicateYear, predicateCityName);
    }
}
