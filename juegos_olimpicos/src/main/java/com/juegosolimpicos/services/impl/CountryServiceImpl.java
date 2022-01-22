package com.juegosolimpicos.services.impl;

import com.juegosolimpicos.exceptions.*;
import com.juegosolimpicos.mapper.CityMapper;
import com.juegosolimpicos.mapper.CountryMapper;
import com.juegosolimpicos.model.entities.sql.CountryEntity;
import com.juegosolimpicos.model.repositories.ICountryRepository;
import com.juegosolimpicos.model.repositories.IHeadquarterRepository;
import com.juegosolimpicos.openapi.model.CityDto;
import com.juegosolimpicos.openapi.model.CountryDto;
import com.juegosolimpicos.openapi.model.FindCountryRequestDto;
import com.juegosolimpicos.openapi.model.FindCountryResponseDto;
import com.juegosolimpicos.services.ICountryService;
import com.juegosolimpicos.services.predicates.CountryPredicates;
import com.juegosolimpicos.services.utils.PageableUtils;
import com.juegosolimpicos.ws.aop.ILogeable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Class to perform the different logical
 * operations with the repository of the
 * country entity
 *
 * @author ncid.quindel.com
 */

@Service
@Transactional
@ILogeable
public class CountryServiceImpl implements ICountryService {

    private static final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Autowired
    private ICountryRepository countryRepository;

    @Autowired
    private IHeadquarterRepository headquarterRepository;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private CityMapper cityMapper;


    /**
     * Function to return all countries
     *
     * @return list of CountryDto
     */
    @Override
    public List<CountryDto> getCountries() {
        return countryRepository.findAll().stream()
                .map(countryMapper::toCountryDto)
                .collect(Collectors.toList());
    }
    /**
     * Function to return all cities of the country
     * @param id
     * @return list of CityDto
     */
    @Override
    public List<CityDto> getCitiesOfCountry(Integer id) {
        return countryRepository.getAllCities(id).stream()
                .map(cityMapper::toCityDto)
                .collect(Collectors.toList());
    }

    /**
     * save or update an entity
     *
     * @param countryDto
     * @return entity CountryDto
     */
    @Override
    public CountryDto save(CountryDto countryDto) throws BaseException {
        Objects.requireNonNull(countryDto);
        Objects.requireNonNull(countryDto.getName());
        Objects.requireNonNull(countryDto.getCode());

        Optional<CountryEntity> countryEntityOptional = this.countryRepository.findByNameOrCode(countryDto.getName(), countryDto.getCode())
                .filter(e -> Boolean.FALSE.equals(e.getId().equals(countryDto.getId())));
        if (countryEntityOptional.isPresent()) {
            throw new ConflictException(ErrorCodeEnum.EXISTS_COUNTRY, String.format("The country %s with the reported data already exists", countryEntityOptional.get()));
        }
        CountryEntity countryEntity;
        if (Objects.nonNull(countryDto.getId())) {
            countryEntity = this.countryRepository.findById(countryDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_COUNTRY, String.format("There is no country with the id %d", countryDto.getId())));
        } else {
            countryEntity = new CountryEntity();
            countryEntity.setId(this.countryRepository.getNextValId());
        }
        countryEntity.setName(countryDto.getName());
        countryEntity.setValue(countryDto.getValue());
        countryEntity.setCode(countryDto.getCode());

        countryEntity = this.countryRepository.save(countryEntity);

        return this.countryMapper.toCountryDto(countryEntity);

    }

    /**
     * @param id
     * @throws EntityNotFoundException if entity not exists
     * @throws FailedDependencyException if the country has cities
     */
    @Override
    public void deleteById(Integer id) throws BaseException {
        Objects.requireNonNull(id);

        if (Boolean.TRUE.equals(this.headquarterRepository.existsByCountryId(id))){
            throw new FailedDependencyException(ErrorCodeEnum.FAILED_DEPENDENCY_COUNTRY, String.format("The country with the reported id %s already has cities with headquarters assigned", id));
        }
        this.countryRepository.delete(this.countryRepository.findById(id)
                .orElseThrow(()->new  EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_COUNTRY, String.format("There is no country with the id %d", id))));


    }

    /**
     * @param id
     * @return entity CountryDto
     * @throws EntityNotFoundException if entity not exists
     */
    @Override
    public CountryDto getById(Integer id) throws BaseException {
        Objects.requireNonNull(id);
        CountryEntity countryEntity = this.countryRepository.findById(id)
                .orElseThrow(()->new  EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_COUNTRY, String.format("There is no country with the id %d", id)));
        return this.countryMapper.toCountryDto(countryEntity);
    }

    public FindCountryResponseDto findCountryList(FindCountryRequestDto countryRequestDto, Integer offSet, Integer limitParam,
                                                  String sortDirection,String sortProperty) {
        FindCountryResponseDto countryResponse = new FindCountryResponseDto();
        String property = Optional.ofNullable(sortProperty).orElse("id");
        Pageable page = PageableUtils.createPageable(offSet,limitParam, sortDirection, property);
        Page<CountryEntity> countryPage = this.countryRepository.findAll(
                  CountryPredicates.findCountryList(countryRequestDto), page);
          if (!countryPage.getContent().isEmpty()) {
            countryResponse = this.countryMapper.toFindCountryResponseDto(countryPage);
          }

        return countryResponse;

    }

}
