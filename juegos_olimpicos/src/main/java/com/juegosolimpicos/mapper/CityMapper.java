package com.juegosolimpicos.mapper;

import com.juegosolimpicos.model.entities.sql.CityEntity;
import com.juegosolimpicos.openapi.model.CityDto;
import com.juegosolimpicos.openapi.model.FindCityResponseDto;
import com.juegosolimpicos.openapi.model.PagedDto;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.Objects;

/**
 * Interface to create the entity-to-DTO
 * and DTO-to-entity mappers of the city class
 *
 * @author ncid.quindel.com
 */

@Mapper(componentModel = "spring", uses = CountryMapper.class)
public interface CityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "country", source = "country")
    CityEntity toCityEntity(CityDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "value", source = "value")
    @Mapping(target = "country", source = "country")
    CityDto toCityDto(CityEntity entity);

    default FindCityResponseDto toFindCityResponseDto(Page<CityEntity> page) {
        if (Objects.isNull(page)) {
            return null;
            }
        FindCityResponseDto findCityResponseDto = new FindCityResponseDto();
        PagedDto pagedDto = new PagedDto();
        pagedDto.setClassName("CityDto");
        pagedDto.setEmpty(page.isEmpty());
        pagedDto.setFirst(page.isFirst());
        pagedDto.setLast(page.isLast());
        pagedDto.setOffset(page.getNumber());
        pagedDto.setLimit(page.getSize());
        pagedDto.setNumberOfElements(page.getNumberOfElements());
        pagedDto.setTotalPages(page.getTotalPages());
        pagedDto.setTotalElements(page.getTotalElements());


        findCityResponseDto.setCities(page.map(this::toCityDto).getContent());
        findCityResponseDto.setPagination(pagedDto);

        return findCityResponseDto;
    }

}
