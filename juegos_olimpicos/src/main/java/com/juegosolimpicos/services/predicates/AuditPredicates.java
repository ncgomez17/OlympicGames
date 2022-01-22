package com.juegosolimpicos.services.predicates;

import com.juegosolimpicos.model.entities.mongo.QAuditDocumentEntity;
import com.juegosolimpicos.openapi.model.FindAuditRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Objects;

public class AuditPredicates {

    public static Predicate findAuditList(FindAuditRequestDto auditRequestDto){
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression predicateUser = null;
        BooleanExpression predicateEntity = null;
        BooleanExpression predicateLastModifiedDate = null;

        QAuditDocumentEntity audit = QAuditDocumentEntity.auditDocumentEntity;

        if(Objects.nonNull(auditRequestDto.getUser())){
            predicateUser = audit.lastModifiedBy.contains(auditRequestDto.getUser());
        }
        if(Objects.nonNull(auditRequestDto.getEntity())){
            predicateEntity = audit.entity.contains(auditRequestDto.getEntity());
        }
        if(Objects.nonNull(auditRequestDto.getLastModifiedDate())){
            predicateLastModifiedDate = audit.lastModifiedDate.stringValue().contains(auditRequestDto.getLastModifiedDate().toString());
        }

        return builder.orAllOf(predicateUser,predicateEntity,predicateLastModifiedDate);
    }
}
