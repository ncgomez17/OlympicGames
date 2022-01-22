package com.juegosolimpicos.model.repositories;

import com.juegosolimpicos.model.entities.mongo.AuditLogEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuditLogRepository extends MongoRepository<AuditLogEntity, ObjectId>,
        QuerydslPredicateExecutor<AuditLogEntity> {
}
