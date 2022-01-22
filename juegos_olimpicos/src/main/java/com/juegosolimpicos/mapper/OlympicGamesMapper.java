package com.juegosolimpicos.mapper;


import com.juegosolimpicos.dto.IOlympicGamesDto;
import com.juegosolimpicos.openapi.model.FindOlympicGamesResponseDto;
import com.juegosolimpicos.openapi.model.OlympicGamesDto;
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

@Mapper(componentModel = "spring")
public interface OlympicGamesMapper {

    @Mapping(target = "countryId", source = "countryId")
    @Mapping(target = "countryName", source = "countryName")
    @Mapping(target = "cityId", source = "cityId")
    @Mapping(target = "cityName", source = "cityName")
    @Mapping(target = "cityValue", source = "value")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "countHeadquarters", source = "countHeadquarters")
    OlympicGamesDto toOlympicGamesDto(IOlympicGamesDto iOlympicGamesDto);

    default FindOlympicGamesResponseDto toFindOlympicGamesResponseDto(Page<IOlympicGamesDto> page) {
        if (Objects.isNull(page)) {
            return null;
        }
        FindOlympicGamesResponseDto findOlympicGamesResponseDto = new FindOlympicGamesResponseDto();
        PagedDto pagedDto = new PagedDto();
        pagedDto.setClassName("OlympicGamesDto");
        pagedDto.setEmpty(page.isEmpty());
        pagedDto.setFirst(page.isFirst());
        pagedDto.setLast(page.isLast());
        pagedDto.setOffset(page.getNumber());
        pagedDto.setLimit(page.getSize());
        pagedDto.setNumberOfElements(page.getNumberOfElements());
        pagedDto.setTotalPages(page.getTotalPages());
        pagedDto.setTotalElements(page.getTotalElements());


        findOlympicGamesResponseDto.setOlympicGames(page.map(this::toOlympicGamesDto).getContent());
        findOlympicGamesResponseDto.setPagination(pagedDto);

        return findOlympicGamesResponseDto;
    }

}
