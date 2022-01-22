package com.juegosolimpicos.model.repositories;

import com.juegosolimpicos.model.entities.sql.CityEntity;;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface ICityRepository extends JpaRepository<CityEntity, Integer>,
        QuerydslPredicateExecutor<CityEntity> {

    Optional<CityEntity> findByNameAndCountryId(String name, Integer countryId);

    @Query(value = "SELECT COALESCE(MAX(c.id),0)+1 FROM CityEntity c")
    Integer getNextValId();


}
