package com.juegosolimpicos.mapper;


import com.juegosolimpicos.model.entities.sql.CountryEntity;
import com.juegosolimpicos.openapi.model.CountryDto;
import com.juegosolimpicos.openapi.model.FindCountryResponseDto;
import com.juegosolimpicos.openapi.model.PagedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.Objects;

/**
 * Interface to create the entity-to-DTO, DTO-to-entity
 * and Page<CountryEntity> to findCountryResponseDto mappers of the country class
 *
 * @author ncid.quindel.com
 */
@Mapper(componentModel = "spring", uses = CityMapper.class)
public interface CountryMapper {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "code", source = "code")
  @Mapping(target = "value", source = "value")
  //            @Mapping(target = "cities", source = "cities",qualifiedByName =
  // "toReduceCityEntity")
  CountryEntity toCountryEntity(CountryDto dto);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "name", source = "name")
  @Mapping(target = "code", source = "code")
  @Mapping(target = "value", source = "value")
  //            @Mapping(target = "cities", source = "cities", qualifiedByName = "toReduceCityDto")
  CountryDto toCountryDto(CountryEntity entity);

  default FindCountryResponseDto toFindCountryResponseDto(Page<CountryEntity> page) {
      if (Objects.isNull(page)) {
          return null;
      }
      FindCountryResponseDto findCountryResponseDto = new FindCountryResponseDto();
      PagedDto pagedDto = new PagedDto();
      pagedDto.setClassName("CountryDto");
      pagedDto.setEmpty(page.isEmpty());
      pagedDto.setFirst(page.isFirst());
      pagedDto.setLast(page.isLast());
      pagedDto.setOffset(page.getNumber());
      pagedDto.setLimit(page.getSize());
      pagedDto.setNumberOfElements(page.getNumberOfElements());
      pagedDto.setTotalPages(page.getTotalPages());
      pagedDto.setTotalElements(page.getTotalElements());


      findCountryResponseDto.setCountries(page.map(this::toCountryDto).getContent());
      findCountryResponseDto.setPagination(pagedDto);

      return findCountryResponseDto;
  }
}
