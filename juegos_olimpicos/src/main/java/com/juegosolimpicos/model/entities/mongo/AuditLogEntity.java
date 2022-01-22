package com.juegosolimpicos.model.entities.mongo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.EntityListeners;
import java.util.Date;

@Document
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AuditLogEntity <ID,T> {

    @Id
    private ObjectId _id;

    @Field("id")
    private ID id;

    @Field("entity")
    private String entity;

    @Field("action")
    private String action;

    @Field("lastModifiedBy")
    @LastModifiedBy
    private String lastModifiedBy;

    @Field("data")
    private T data;

    @Field("lastModifiedDate")
    @LastModifiedDate
    private Date lastModifiedDate;

    @Field("version")
    private Integer version;

    @Field("previous")
    private ObjectId previous;

    public AuditLogEntity(ID id, T data) {
        this.id = id;
        this.data = data;
    }
    public AuditLogEntity(){
    }

}
