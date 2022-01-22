package com.juegosolimpicos.model.entities.mongo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;


@Document(collection="usuarios")
@Data
public class UserEntity {

    @Id
    private ObjectId id;

    @Field("nickName")
    private String nickName;

    @Field("password")
    private String password;

    @Field("role")
    private List<String> role;
}
