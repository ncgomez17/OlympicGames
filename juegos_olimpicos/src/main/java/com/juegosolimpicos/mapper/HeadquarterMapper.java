package com.juegosolimpicos.mapper;

import com.juegosolimpicos.model.entities.sql.HeadquarterEntity;
import com.juegosolimpicos.openapi.model.FindHeadquarterResponseDto;
import com.juegosolimpicos.openapi.model.HeadquarterDto;
import com.juegosolimpicos.openapi.model.PagedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.Objects;

/**
 * Interface to create the entity-to-DTO
 * and DTO-to-entity mappers of the headquarter class
 * @author ncid.quindel.com
 */

@Mapper(componentModel = "spring", uses = {CityMapper.class, TypeJjooMapper.class})
public interface HeadquarterMapper {

    @Mapping(target = "id.year", source = "id.year")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "typeJjoo", source = "id.type")
    HeadquarterEntity toHeadquarterEntity(HeadquarterDto dto);

    @Mapping(target = "id.year", source = "id.year")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "id.type", source = "typeJjoo")
    HeadquarterDto toHeadquarterDto(HeadquarterEntity entity);

    default FindHeadquarterResponseDto toFindHeadquarterResponseDto(Page<HeadquarterEntity> page) {
        if (Objects.isNull(page)) {
            return null;
        }
        FindHeadquarterResponseDto findHeadquarterResponseDto = new FindHeadquarterResponseDto();
        PagedDto pagedDto = new PagedDto();
        pagedDto.setClassName("HeadquarterDto");
        pagedDto.setEmpty(page.isEmpty());
        pagedDto.setFirst(page.isFirst());
        pagedDto.setLast(page.isLast());
        pagedDto.setOffset(page.getNumber());
        pagedDto.setLimit(page.getSize());
        pagedDto.setNumberOfElements(page.getNumberOfElements());
        pagedDto.setTotalPages(page.getTotalPages());
        pagedDto.setTotalElements(page.getTotalElements());


        findHeadquarterResponseDto.setHeadquarters(page.map(this::toHeadquarterDto).getContent());
        findHeadquarterResponseDto.setPagination(pagedDto);

        return findHeadquarterResponseDto;
    }

}
