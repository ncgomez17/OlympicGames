package com.juegosolimpicos.services.utils;

import com.juegosolimpicos.model.entities.sql.HeadquarterPk;
import com.juegosolimpicos.openapi.model.CityDto;
import com.juegosolimpicos.openapi.model.CountryDto;
import com.juegosolimpicos.openapi.model.HeadquarterDto;
import com.juegosolimpicos.openapi.model.TypeJjooDto;

import java.lang.reflect.Field;
import java.util.Objects;

public class ObjectUtils {




  public static  Object getIdEntity(Object entity) throws NoSuchFieldException, IllegalAccessException {
    Objects.requireNonNull(entity);
    if (entity.getClass().getSimpleName().equals("HeadquarterDto")) {
      HeadquarterPk id = new HeadquarterPk();
      HeadquarterDto dto = (HeadquarterDto) entity;
      id.setYear(dto.getId().getYear());
      TypeJjooDto type =(TypeJjooDto) dto.getId().getType();
      id.setTypeJjoo(type.getId());
      return id;
    } else {
      Field fieldId = entity.getClass().getDeclaredField("id");
      fieldId.setAccessible(true);
      Integer id = (Integer) fieldId.get(entity);
      return id;
    }
  }
  public static  Object getData(Object entity) {
    Objects.requireNonNull(entity);
    switch(entity.getClass().getSimpleName())
    {
      case "CountryDto" :
        CountryDto country = (CountryDto) entity;
        country.setId(null);
        entity = country;
        break;

      case "CityDto":
        CityDto city= (CityDto) entity;
        city.setId(null);
        entity = city;
        break;

      case "HeadquarterDto" :
        HeadquarterDto headquarter = (HeadquarterDto) entity;
        headquarter.setId(null);
        entity = headquarter;
        break;

      case "TypeJjooDto" :
        TypeJjooDto type = (TypeJjooDto) entity;
        type.setId(null);
        entity = type;
        break;
    }
    return entity;
  }

}
