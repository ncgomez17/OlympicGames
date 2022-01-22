package com.juegosolimpicos.model.repositories;

import com.juegosolimpicos.model.entities.sql.CityEntity;
import com.juegosolimpicos.model.entities.sql.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICountryRepository extends JpaRepository<CountryEntity, Integer>,
        QuerydslPredicateExecutor<CountryEntity>{

    Optional<CountryEntity> findByNameOrCode(String name, String code);

    @Query(value = "SELECT c FROM CityEntity c where c.country.id = :countryId order by c.id")
    List<CityEntity> getAllCities(@Param("countryId") Integer countryId);

    @Query(value = "SELECT COALESCE(MAX(c.id),0)+1 FROM CountryEntity c")
    Integer getNextValId();

}
