package com.juegosolimpicos.services.impl;

import com.juegosolimpicos.mapper.TypeJjooMapper;
import com.juegosolimpicos.model.entities.sql.TypeJjooEntity;
import com.juegosolimpicos.model.repositories.IHeadquarterRepository;
import com.juegosolimpicos.model.repositories.ITypeJjooRepository;
import com.juegosolimpicos.openapi.model.TypeJjooDto;
import com.juegosolimpicos.services.ITypeJjooService;
import com.juegosolimpicos.exceptions.BaseException;
import com.juegosolimpicos.exceptions.EntityNotFoundException;
import com.juegosolimpicos.exceptions.ErrorCodeEnum;
import com.juegosolimpicos.exceptions.FailedDependencyException;
import com.juegosolimpicos.ws.aop.ILogeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class to perform the different logical
 * operations with the repository of the
 * TypeJjoo entity
 * @author ncid.quindel.com
 */

@Service
@Transactional
@ILogeable
public class TypeJjooServiceImpl implements ITypeJjooService {

    @Autowired
    private ITypeJjooRepository typeJjooRepository;

    @Autowired
    private IHeadquarterRepository headquarterRepository;

    @Autowired
    private TypeJjooMapper typeJjooMapper;

    /**
     * Function to return all typesJjoo
     * @return list of TypeJjooDto
     */
    @Override
    public List<TypeJjooDto> getTypesJjoo() {
        return typeJjooRepository.findAll().stream()
                .map(typeJjooMapper::toTypeJjooDto)
                .collect(Collectors.toList());
    }

//    /**
//     * save or update an entity
//     * @param typeJjooDto
//     * @return entity TypeJjooDto
//     */
//    @Override
//    public TypeJjooDto save(TypeJjooDto typeJjooDto) {
//        Objects.requireNonNull(typeJjooDto);
//        Objects.requireNonNull(typeJjooDto.getId());
//        Objects.requireNonNull(typeJjooDto.getDescription());
//
//        Boolean exists = this.typeJjooRepository.existsByDescription(typeJjooDto.getDescription().toString());
//        if(exists) {
//            throw new HttpClientErrorException(HttpStatus.CONFLICT, String.format("There is already a typeJjoo %s with that description",
//                    typeJjooDto.toString()));
//        }
//
//        TypeJjooEntity typeJjooEntity;
//        if(Objects.nonNull(typeJjooDto.getId())){
//            typeJjooEntity = this.typeJjooRepository.findById(typeJjooDto.getId())
//                    .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("There is no typeJjoo with the id %d", typeJjooDto.getId())));
//        }else {
//            typeJjooEntity = new TypeJjooEntity();
//            typeJjooEntity.setId(this.typeJjooRepository.getNextValId());
//        }
//
//        typeJjooEntity.setDescription(typeJjooDto.getDescription());
//
//        typeJjooEntity = this.typeJjooRepository.save(typeJjooEntity);
//
//        return this.typeJjooMapper.toTypeJjooDto(typeJjooEntity);
//
//    }

    /**
     * @throws EntityNotFoundException if entity not exists
     * @param id
     */
    @Override
    public void deleteById(Integer id) throws BaseException {
        Objects.requireNonNull(id);

        if (Boolean.TRUE.equals(this.headquarterRepository.existsByTypeJjoo(id))){
            throw new FailedDependencyException(ErrorCodeEnum.FAILED_DEPENDECY_TYPEJJOO, String.format("The typeJjoo with the reported id %s already has headquarters assigned", id));
        }
        this.typeJjooRepository.delete(this.typeJjooRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_TYPEJJOO, String.format("There is no type with the id %d", id))));
    }

    /**
     * @throws EntityNotFoundException if entity not exists
     * @param id
     * @return entity TypeJjooDto
     */
    @Override
    public TypeJjooDto getById(Integer id) throws BaseException {
        Objects.requireNonNull(id);
        TypeJjooEntity typeJjooEntity = this.typeJjooRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_TYPEJJOO, String.format("There is no type with the id %d", id)));
        return this.typeJjooMapper.toTypeJjooDto(typeJjooEntity);
    }
}
