package com.juegosolimpicos.model.entities.sql;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class to represent Headquearter key from Headquearter
 * entities
 * @author ncid.quindel.com
 */

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeadquarterPk implements Serializable {

    @Column(name = "año")
    private Integer year;

    @Column(name = "tipo")
    private Integer typeJjoo;
}
