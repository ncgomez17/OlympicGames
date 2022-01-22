package com.juegosolimpicos.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

import java.util.Objects;

@Mapper
public interface ObjectIdMapper {

    static String map(final ObjectId value){
        return Objects.isNull(value) ? null :value.toString();
    }

    static ObjectId map(final String value){
        return Objects.isNull(value) ? null: new ObjectId(value);
    }

    static ObjectId mapper( final ObjectId value){
        return Objects.isNull(value) ? null: new ObjectId(value.toString());
    }

}
