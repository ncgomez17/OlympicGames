package com.juegosolimpicos.model.entities.sql;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * Class to represent City entites from the BD
 * mapped with JPA
 * @author ncid.quindel.com
 */

@Entity
@Table(name = "ciudad")
@Data
public class CityEntity {

    @Id
    @Column(name = "id_ciudad")
    private Integer id;

    @Column(name = "nombre_ciudad")
    private String name;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "id_pais", nullable = false)
    private CountryEntity country;

    @Column(name = "valor_ciudad")
    private Integer value;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;


}

