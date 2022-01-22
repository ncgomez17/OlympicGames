package com.juegosolimpicos.services.impl;

import com.juegosolimpicos.exceptions.*;
import com.juegosolimpicos.mapper.CityMapper;
import com.juegosolimpicos.model.entities.sql.CityEntity;
import com.juegosolimpicos.model.repositories.ICityRepository;
import com.juegosolimpicos.model.repositories.ICountryRepository;
import com.juegosolimpicos.model.repositories.IHeadquarterRepository;
import com.juegosolimpicos.openapi.model.CityDto;
import com.juegosolimpicos.openapi.model.FindCityRequestDto;
import com.juegosolimpicos.openapi.model.FindCityResponseDto;
import com.juegosolimpicos.services.ICityService;
import com.juegosolimpicos.services.predicates.CityPredicates;
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
 * Class to perform the different logical
 * operations with the repository of the
 * city entity
 *
 * @author ncid.quindel.com
 */

@Service
@Transactional
@ILogeable
public class CityServiceImpl implements ICityService {

    @Autowired
    private ICityRepository cityRepository;

    @Autowired
    private ICountryRepository countryRepository;

    @Autowired
    private IHeadquarterRepository headquarterRepository;

    @Autowired
    private CityMapper cityMapper;

    /**
     * Function to return all cities
     * @return list of CityDto
     */
    @Override
    public List<CityDto> getCities() {
        return cityRepository.findAll().stream()
                .map(cityMapper::toCityDto)
                .collect(Collectors.toList());
    }

    /**
     * save or update an entity
     *
     * @param cityDto
     * @return entity CityDto
     */
    @Override
    public CityDto save(CityDto cityDto) throws BaseException {
        Objects.requireNonNull(cityDto);
        Objects.requireNonNull(cityDto.getCountry());
        Objects.requireNonNull(cityDto.getName());

        //
        Optional<CityEntity> cityEntityOptional = this.cityRepository.findByNameAndCountryId(cityDto.getName(), cityDto.getCountry().getId())
                .filter(e -> Boolean.FALSE.equals(e.getId().equals(cityDto.getId())));
        if (cityEntityOptional.isPresent()) {
            throw new ConflictException(ErrorCodeEnum.EXISTS_CITY, String.format("The city %s with the reported data already exists", cityEntityOptional.get()));
        }

        CityEntity cityEntity;
        if (Objects.nonNull(cityDto.getId())) {
            cityEntity = this.cityRepository.findById(cityDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_CITY, String.format("There is no city with the id %d", cityDto.getId())));
        } else {
            cityEntity = new CityEntity();
            cityEntity.setId(this.cityRepository.getNextValId());
            cityEntity.setCountry(this.countryRepository.getById(cityDto.getCountry().getId()));
        }

        cityEntity.setName(cityDto.getName().trim());
        cityEntity.setValue(cityDto.getValue());
        cityEntity.setLatitude(cityDto.getLatitude());
        cityEntity.setLongitude(cityDto.getLongitude());

        cityEntity = this.cityRepository.save(cityEntity);

        return this.cityMapper.toCityDto(cityEntity);
    }
    /**
     * Count the times a city has hosted
     * @param id
     * @return Integer
     */
    public Integer getCountHeadquarters(Integer id){
        Objects.requireNonNull(id);
        return this.headquarterRepository.countByCityId(id);
    }

    /**
     * @param id
     * @throws EntityNotFoundException if entity not exists
     */
    @Override
    public void deleteById(Integer id)throws BaseException {
        Objects.requireNonNull(id);

        if (this.headquarterRepository.existsByCityId(id)) {
            throw new FailedDependencyException(ErrorCodeEnum.FAILED_DEPENDECY_CITY, String.format("The city with the reported id %s already has headquarters assigned", id));
        }
        this.cityRepository.delete(this.cityRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_CITY, String.format("There is no city with the id %d", id))));

    }

    /**
     * @param id
     * @return entity CityDto
     */
    @Override
    public CityDto getById(Integer id) throws BaseException {
        Objects.requireNonNull(id);
        CityEntity cityEntity = this.cityRepository.findById(id)
                .orElseThrow(()->new  EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_CITY, String.format("There is no city with the id %d", id)));
        return this.cityMapper.toCityDto(cityEntity);
    }

    public FindCityResponseDto findCityList(FindCityRequestDto cityRequestDto, Integer offSet, Integer limitParam,
                                            String sortDirection, String sortProperty) {
        FindCityResponseDto cityResponse = new FindCityResponseDto();
        String property = Optional.ofNullable(sortProperty).orElse("id");
        Pageable page = PageableUtils.createPageable(offSet,limitParam, sortDirection, property);
        Page<CityEntity> cityPage = this.cityRepository.findAll(CityPredicates.findCityList(cityRequestDto), page);
        if (!cityPage.getContent().isEmpty()) {
            cityResponse = this.cityMapper.toFindCityResponseDto(cityPage);
        }

        return cityResponse;

    }
}
