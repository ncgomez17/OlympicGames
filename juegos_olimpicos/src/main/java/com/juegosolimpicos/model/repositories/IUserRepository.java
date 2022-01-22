package com.juegosolimpicos.model.repositories;

import com.juegosolimpicos.model.entities.mongo.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<UserEntity, ObjectId> {

    Optional<UserEntity> findById(ObjectId id);

    Optional<UserEntity> findByNickName(String name);

}
