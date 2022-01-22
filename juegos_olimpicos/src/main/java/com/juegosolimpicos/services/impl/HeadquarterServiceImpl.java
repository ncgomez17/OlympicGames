package com.juegosolimpicos.services.impl;

import com.juegosolimpicos.dto.IOlympicGamesDto;
import com.juegosolimpicos.exceptions.BaseException;
import com.juegosolimpicos.exceptions.ConflictException;
import com.juegosolimpicos.exceptions.EntityNotFoundException;
import com.juegosolimpicos.exceptions.ErrorCodeEnum;
import com.juegosolimpicos.mapper.CityMapper;
import com.juegosolimpicos.mapper.HeadquarterMapper;
import com.juegosolimpicos.mapper.OlympicGamesMapper;
import com.juegosolimpicos.mapper.TypeJjooMapper;
import com.juegosolimpicos.model.entities.sql.HeadquarterEntity;
import com.juegosolimpicos.model.entities.sql.HeadquarterPk;
import com.juegosolimpicos.model.repositories.IHeadquarterRepository;
import com.juegosolimpicos.openapi.model.*;
import com.juegosolimpicos.services.IHeadquarterService;
import com.juegosolimpicos.services.predicates.HeadquarterPredicates;
import com.juegosolimpicos.services.utils.PageableUtils;
import com.juegosolimpicos.ws.aop.ILogeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class to perform the different logical operations with the repository of the headquarter entity
 *
 * @author ncid.quindel.com
 */
@Service
@Transactional
@ILogeable
public class HeadquarterServiceImpl implements IHeadquarterService {

  @Autowired private IHeadquarterRepository headquarterRepository;

  @Autowired private HeadquarterMapper headquarterMapper;

  @Autowired private TypeJjooMapper typeJjooMapper;

  @Autowired private CityMapper cityMapper;

  @Autowired private OlympicGamesMapper olympicGamesMapper;

  @Autowired private CityServiceImpl cityService;

  @Autowired private TypeJjooServiceImpl typeJjooService;

  /**
   * Function to return all headquarters
   *
   * @return list of HeadquarterDto
   */
  @Override
  public List<HeadquarterDto> getHeadquarters() {
    return this.headquarterRepository.findAll().stream()
        .map(headquarterMapper::toHeadquarterDto)
        .collect(Collectors.toList());
  }

  public List<OlympicGamesDto> getOlympicGames() {
    return this.headquarterRepository.findOlympicGames().stream()
        .map(this.olympicGamesMapper::toOlympicGamesDto)
        .collect(Collectors.toList());
  }

  /**
   * save or update an entity
   *
   * @param headquarterDto
   * @return entity HeadquarterDto
   */
  @Override
  public HeadquarterDto save(HeadquarterDto headquarterDto) throws BaseException {
    Objects.requireNonNull(headquarterDto);
    Objects.requireNonNull(headquarterDto.getId().getYear());
    Objects.requireNonNull(headquarterDto.getId().getType());
    Objects.requireNonNull(headquarterDto.getCity());
    System.out.println(headquarterDto);
    Boolean exists =
        this.headquarterRepository.existsByYearAndType(
            headquarterDto.getId().getYear(), headquarterDto.getId().getType().getId());

    if (exists) {
      throw new ConflictException(
          ErrorCodeEnum.EXISTS_HEADQUARTER,
          String.format(
              "There is already a headquarter %s that was held in that year and with that type",
              headquarterDto));
    }

    HeadquarterEntity headquarterEntity = new HeadquarterEntity();
    HeadquarterPk headquarterId =
        HeadquarterPk.builder()

            .typeJjoo(headquarterDto.getCity().getId())
            .year(headquarterDto.getId().getYear())
            .build();

    headquarterEntity.setId(headquarterId);
    headquarterEntity.setTypeJjoo(
        this.typeJjooMapper.toTypeJjooEntity(
            this.typeJjooService.getById(headquarterDto.getId().getType().getId())));
    headquarterEntity.setCity(
        this.cityMapper.toCityEntity(this.cityService.getById(headquarterDto.getCity().getId())));

    headquarterEntity = this.headquarterRepository.save(headquarterEntity);

    return this.headquarterMapper.toHeadquarterDto(headquarterEntity);
  }

  /**
   * @param year
   * @param typeId
   * @throws EntityNotFoundException if entity not exists
   */
  @Override
  public void deleteById(Integer year, Integer typeId) throws BaseException {
    Objects.requireNonNull(year);
    Objects.requireNonNull(typeId);

    HeadquarterPk headquarterPk = HeadquarterPk.builder().year(year).typeJjoo(typeId).build();
    this.headquarterRepository.delete(
        this.headquarterRepository
            .findById(headquarterPk)
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        ErrorCodeEnum.NOT_FOUND_HEADQUARTER,
                        String.format(
                            "There is no headquarter with the id %s", headquarterPk))));
    ;
  }

  /**
   * @param year
   * @param typeId
   * @return entity CityDto
   * @throws EntityNotFoundException if entity not exists
   */
  @Override
  public HeadquarterDto getById(Integer year, Integer typeId) throws BaseException {
    Objects.requireNonNull(year);
    Objects.requireNonNull(typeId);

    HeadquarterPk headquarterPk = HeadquarterPk.builder().year(year).typeJjoo(typeId).build();

    HeadquarterEntity headquarterEntity = this.headquarterRepository.findById(headquarterPk)
            .orElseThrow(                () ->
                    new EntityNotFoundException(
                            ErrorCodeEnum.NOT_FOUND_HEADQUARTER,
                            String.format(
                                    "There is no headquarter with the id %s", headquarterPk)));
    return this.headquarterMapper.toHeadquarterDto(headquarterEntity);
  }

  public FindHeadquarterResponseDto findHeadquarterList(FindHeadquarterRequestDto headquarterRequestDto, Integer offSet, Integer limitParam,
                                          String sortDirection, String sortProperty) {

    FindHeadquarterResponseDto headquarterResponse = new FindHeadquarterResponseDto();
    String property = Optional.ofNullable(sortProperty).orElse("id.year");
    Pageable page = PageableUtils.createPageable(offSet,limitParam, sortDirection, property);
    Page<HeadquarterEntity> headquarterPage = this.headquarterRepository.findAll(HeadquarterPredicates.findHeadquarterList(headquarterRequestDto), page);
    if (!headquarterPage.getContent().isEmpty()) {
      headquarterResponse = this.headquarterMapper.toFindHeadquarterResponseDto(headquarterPage);
    }

    return headquarterResponse;

  }
  public FindOlympicGamesResponseDto findOlympicGamesList(Integer offSet, Integer limitParam,
                                                          String sortDirection, String sortProperty) {

    FindOlympicGamesResponseDto olympicGamesResponse = new FindOlympicGamesResponseDto();
    String property = Optional.ofNullable(sortProperty).orElse("countryId");
    Pageable page = PageableUtils.createPageable(offSet,limitParam, sortDirection, property);
    Page<IOlympicGamesDto> olympicGamesPage = this.headquarterRepository.findOlympicGamesList(page);
    if (!olympicGamesPage.getContent().isEmpty()) {
      olympicGamesResponse = this.olympicGamesMapper.toFindOlympicGamesResponseDto(olympicGamesPage);
    }

    return olympicGamesResponse;

  }

}
