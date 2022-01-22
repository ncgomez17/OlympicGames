package com.juegosolimpicos.model.repositories;

import com.juegosolimpicos.model.entities.sql.TypeJjooEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeJjooRepository extends JpaRepository<TypeJjooEntity, Integer>,
        QuerydslPredicateExecutor<TypeJjooEntity> {

    //    @Query("SELECT CASE WHEN Count(c) > 0 " +
//            " then true else false end from TypeJjooEntity t" +
//            " where lower(t.description) like lower(:description)")
//    Boolean existsByDescription(@Param("description") String description);
//
//    @Query(value = "SELECT Max(t.id)+1 FROM TypeJjooEntity t where t.id IS NOT NULL order by t.id")
//
}
