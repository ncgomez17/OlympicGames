package com.juegosolimpicos.model.repositories;

import com.juegosolimpicos.dto.IOlympicGamesDto;
import com.juegosolimpicos.model.entities.sql.HeadquarterEntity;
import com.juegosolimpicos.model.entities.sql.HeadquarterPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHeadquarterRepository extends JpaRepository<HeadquarterEntity, HeadquarterPk>,
        QuerydslPredicateExecutor<HeadquarterEntity> {

    @Query("SELECT CASE WHEN Count(h) > 0 " +
            " then true else false end from HeadquarterEntity h" +
            " where h.id.year = :year and h.typeJjoo.id = :type")
    boolean existsByYearAndType(@Param("year") Integer year,
                                       @Param("type") Integer type);

    @Query(value = "SELECT CASE WHEN Count(*)>0 then true else false end" +
            " FROM HeadquarterEntity as hq join hq.city as city join city.country as country" +
            " where country.id = :countryId")
    boolean existsByCountryId(@Param("countryId") Integer id);

    @Query(value = "SELECT CASE WHEN Count(*)>0 then true else false end" +
            " FROM HeadquarterEntity as hq join hq.city as city " +
            " where city.id = :cityId")
    boolean existsByCityId(@Param("cityId") Integer id);

    @Query(value = "SELECT CASE WHEN Count(*)>0 then true else false end" +
            " FROM  HeadquarterEntity as hq join hq.typeJjoo as type" +
            " where type.id = :typeJjooId")
    boolean existsByTypeJjoo(@Param("typeJjooId") Integer id);

    Integer countByCityId(@Param("cityId") Integer id);

    @Query("SELECT  country.id as countryId, country.name as countryName, city.id as cityId,city.name as cityName," +
            " COALESCE(city.value,country.value) as value," +
            " headquarter.typeJjoo.description as description," +
            " COUNT(headquarter) as countHeadquarters" +
            " FROM HeadquarterEntity headquarter left join headquarter.typeJjoo as type "+
            "right join headquarter.city as city left join city.country as country" +
            " Group By country.id,country.name,city.id,city.name,headquarter.typeJjoo.description")
    List<IOlympicGamesDto> findOlympicGames();

    @Query("SELECT  country.id as countryId, country.name as countryName, city.id as cityId,city.name as cityName," +
            " COALESCE(city.value,country.value) as value," +
            " headquarter.typeJjoo.description as description," +
            " COUNT(headquarter) as countHeadquarters" +
            " FROM HeadquarterEntity headquarter left join headquarter.typeJjoo as type "+
            "right join headquarter.city as city left join city.country as country" +
            " Group By country.id,country.name,city.id,city.name,headquarter.typeJjoo.description")
    Page<IOlympicGamesDto> findOlympicGamesList(Pageable page);

}
