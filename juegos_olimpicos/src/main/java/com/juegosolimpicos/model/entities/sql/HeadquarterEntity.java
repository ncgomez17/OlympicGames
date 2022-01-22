package com.juegosolimpicos.model.entities.sql;

import lombok.Data;

import javax.persistence.*;

/**
 * Class to represent Headquarter entites from the BD
 * mapped with JPA
 * @author ncid.quindel.com
 */

@Entity
@Table(name = "sede_jjoo")
@Data
public class HeadquarterEntity {

    @EmbeddedId
    private HeadquarterPk id;

    @ManyToOne
    @JoinColumn(name = "sede")
    CityEntity city;

    @MapsId("typeJjoo")
    @ManyToOne
    @JoinColumn(name = "id_tipo_jjoo")
    private TypeJjooEntity typeJjoo;


}


