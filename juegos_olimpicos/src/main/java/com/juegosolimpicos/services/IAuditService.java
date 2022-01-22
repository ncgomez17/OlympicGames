package com.juegosolimpicos.services;

import com.juegosolimpicos.audit.AuditEnum;
import com.juegosolimpicos.exceptions.BaseException;
import com.juegosolimpicos.model.entities.mongo.AuditDocumentEntity;
import com.juegosolimpicos.openapi.model.AuditDto;
import com.juegosolimpicos.openapi.model.FindAuditRequestDto;
import com.juegosolimpicos.openapi.model.FindAuditResponseDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface IAuditService {

    List<AuditDto> getAll();
    AuditDocumentEntity save(Object entity, AuditEnum action) throws  NoSuchFieldException, IllegalAccessException;
    AuditDocumentEntity delete(Integer id, String entity, AuditEnum auditEnum) throws NoSuchFieldException, IllegalAccessException, BaseException;
    FindAuditResponseDto findAuditList(FindAuditRequestDto auditRequestDto, Integer offSet, Integer limitParam,
                                       String sortDirection, String sortProperty) throws BaseException;
    Object diff(ObjectId objectId) throws BaseException;

}
