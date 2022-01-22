package com.juegosolimpicos.mapper;

import com.juegosolimpicos.model.entities.mongo.UserEntity;
import com.juegosolimpicos.openapi.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Interface to create the entity-to-DTO
 * and DTO-to-entity mappers of the user class
 *
 * @author ncid.quindel.com
 */

@Mapper(componentModel = "spring", uses={ObjectIdMapper.class})
public interface UserMapper {

    @Mapping(target="id", source="id")
    @Mapping(target= "nickName",source="nickName")
    @Mapping(target= "password",source="password")
    @Mapping(target= "role",source="role")
    UserEntity toUserEntity(UserDto source, @MappingTarget UserEntity target);

    @Mapping(target="id", source="id")
    @Mapping(target= "nickName",source="nickName")
    @Mapping(target= "password",source="password")
    @Mapping(target= "role",source="role")
    UserDto toUserDto(UserEntity dto);


}
