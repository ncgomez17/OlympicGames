package com.juegosolimpicos.model.repositories;

import com.juegosolimpicos.model.entities.mongo.AuditDocumentEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuditDocumentRepository extends MongoRepository<AuditDocumentEntity, ObjectId>,
        QuerydslPredicateExecutor<AuditDocumentEntity> {

    @Query(value = "{'id': ?0, 'entity': ?1}",sort = "{'lastModifiedDate' : -1 }")
    List<AuditDocumentEntity> findLastAuditLogEntity(Object entityId,String entity);
}
