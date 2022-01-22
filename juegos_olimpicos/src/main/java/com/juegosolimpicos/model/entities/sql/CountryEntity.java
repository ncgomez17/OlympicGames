package com.juegosolimpicos.model.entities.sql;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Class to represent Country entites from the BD
 * mapped with JPA
 * @author ncid.quindel.com
 */

@Entity
@Table(name = "pais")
@Data
public class CountryEntity{

    @Id
    @Column(name = "id_pais")
    private Integer id;

    @Column(name = "nombre_pais")
    private String name;

    @Column(name = "codigo_pais")
    private String code;

    @Column(name = "valor_pais")
    private Integer value;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<CityEntity> cities;


}
