package com.juegosolimpicos.model.entities.sql;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class to represent TypeJjoo entites from the BD
 * mapped with JPA
 * @author ncid.quindel.com
 */

@Entity
@Table(name = "tipo_jjoo")
@Data
public class TypeJjooEntity{

    @Id
    @Column(name = "id_tipo_jjoo")
    private Integer id;

    @Column(name = "descripcion_tipo")
    private String description;

}


