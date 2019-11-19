package com.kaggle.titanic.dbservice.repository;

import com.kaggle.titanic.dbservice.model.Titanic;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TitanicRepository extends JpaRepository<Titanic,Integer>, JpaSpecificationExecutor<Titanic> {
    Titanic findById(int passenger);
    @Query("select t from titanic t where t.survived = :survived")
    List<Titanic> findBySurvival(@Param("survived") int survived);
    @Transactional
    @Modifying
    @Query(value = "update titanic t set t.pclass = :pclass where t.passenger= :passenger ", nativeQuery = true)
    void updatePClassById(@Param("pclass") int pclass, @Param("passenger") int passenger);
}
