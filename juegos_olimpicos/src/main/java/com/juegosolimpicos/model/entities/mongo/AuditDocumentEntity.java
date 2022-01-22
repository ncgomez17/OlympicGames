package com.juegosolimpicos.model.entities.mongo;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;

@Document(collection="AUDIT_LOG")
public class AuditDocumentEntity extends AuditLogEntity<Object,Object> {

    @Transient
    public static final Integer VERSION_ID = 1;

    public AuditDocumentEntity(){
        this.setVersion(VERSION_ID);
    }

}
