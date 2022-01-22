package com.juegosolimpicos.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juegosolimpicos.audit.AuditEnum;
import com.juegosolimpicos.exceptions.BaseException;
import com.juegosolimpicos.exceptions.EntityNotFoundException;
import com.juegosolimpicos.exceptions.ErrorCodeEnum;
import com.juegosolimpicos.mapper.AuditMapper;
import com.juegosolimpicos.model.entities.mongo.AuditDocumentEntity;
import com.juegosolimpicos.model.repositories.IAuditDocumentRepository;
import com.juegosolimpicos.openapi.model.AuditDto;
import com.juegosolimpicos.openapi.model.FindAuditRequestDto;
import com.juegosolimpicos.openapi.model.FindAuditResponseDto;
import com.juegosolimpicos.services.IAuditService;
import com.juegosolimpicos.services.predicates.AuditPredicates;
import com.juegosolimpicos.services.utils.MapDifferenceUtil;
import com.juegosolimpicos.services.utils.ObjectUtils;
import com.juegosolimpicos.services.utils.PageableUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuditServiceImpl implements IAuditService {

    @Autowired
    private IAuditDocumentRepository auditDocumentRepository;

    @Autowired
    private AuditMapper auditMapper;

    @Override
    public List<AuditDto> getAll(){
        return auditDocumentRepository.findAll().stream().map(auditMapper::toAuditDto).collect(Collectors.toList());
    }

    @Override
    public AuditDocumentEntity save(Object entity, AuditEnum action) throws NoSuchFieldException, IllegalAccessException {
        Objects.requireNonNull(entity);
        String entityName = entity.getClass().getSimpleName().split("(?=[A-Z])")[0];
        Object idEntity = ObjectUtils.getIdEntity(entity);
        AuditDocumentEntity auditDocument = new AuditDocumentEntity();

        Optional<AuditDocumentEntity> id = auditDocumentRepository.findLastAuditLogEntity(idEntity,entityName)
                .stream().findFirst();
        if(id.isPresent()){
            auditDocument.setPrevious(id.get().get_id());
            action = AuditEnum.MODIFY_ACTION;
        }
        auditDocument.setAction(action.toString());
        auditDocument.setData(ObjectUtils.getData(entity));
        auditDocument.setEntity(entityName);
        auditDocument.setId(idEntity);

        return auditDocumentRepository.save(auditDocument);
    }

    @Override
    public AuditDocumentEntity delete(Integer id, String entity, AuditEnum auditEnum) throws NoSuchFieldException, IllegalAccessException, BaseException {
        Objects.requireNonNull(id);
        Objects.requireNonNull(entity);

        AuditDocumentEntity auditDocument = auditDocumentRepository.findLastAuditLogEntity(id,entity)
                .stream().findFirst().map(auditMapper::clone)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_AUDIT, String.format("There is no audit with that id and type %d",id,entity)));
        auditDocument.setAction(auditEnum.toString());
        auditDocument.setPrevious(auditDocument.get_id());
        auditDocument.set_id(null);
        return auditDocumentRepository.save(auditDocument);
    }
    @Override
    public FindAuditResponseDto findAuditList(FindAuditRequestDto auditRequestDto, Integer offSet, Integer limitParam,
                                              String sortDirection, String sortProperty) {
        FindAuditResponseDto auditResponse = new FindAuditResponseDto();
        String property = Optional.ofNullable(sortProperty).orElse("id");
        Pageable page = PageableUtils.createPageable(offSet,limitParam, sortDirection, property);
        Page<AuditDocumentEntity> auditPage = this.auditDocumentRepository.findAll(AuditPredicates.findAuditList(auditRequestDto), page);

        if (!auditPage.getContent().isEmpty()) {
            auditResponse = this.auditMapper.toFindAuditResponseDto(auditPage);
        }

        return auditResponse;

    }
    @Override
    public Object diff(ObjectId objectId) throws BaseException{
        Objects.nonNull(objectId);
        ObjectMapper mapper = new ObjectMapper();
        AuditDocumentEntity auditDocument = this.auditDocumentRepository.findById(objectId)
                .orElseThrow(()->new EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_AUDIT, String.format("There is no audit with the id %d", objectId)));

        AuditDocumentEntity auditPrevious = this.auditDocumentRepository.findById(auditDocument.getPrevious())
                .orElseThrow(()->new EntityNotFoundException(ErrorCodeEnum.NOT_FOUND_AUDIT, String.format("There is no audit with the id %d", objectId)));
        Map result =MapDifferenceUtil.difference(mapper.convertValue(auditDocument.getData(), Map.class),mapper.convertValue(auditPrevious.getData(),Map.class));
        System.out.println(result);
        return result;

    }
}
