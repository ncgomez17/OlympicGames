package com.juegosolimpicos.mapper;

import com.juegosolimpicos.model.entities.sql.TypeJjooEntity;
import com.juegosolimpicos.openapi.model.TypeJjooDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Interface to create the entity-to-DTO
 * and DTO-to-entity mappers of the typeJjoo class
 * @author ncid.quindel.com
 */

@Mapper(componentModel = "spring")
public interface TypeJjooMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    TypeJjooEntity toTypeJjooEntity(TypeJjooDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "description", source = "description")
    TypeJjooDto toTypeJjooDto(TypeJjooEntity entity);
}
