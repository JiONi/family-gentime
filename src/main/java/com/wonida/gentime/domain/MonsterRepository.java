package com.wonida.gentime.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Stream;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    @Query("SELECT m " +
            "FROM Monster m " +
            "ORDER BY m.mobGroup, m.genTerm, m.genTime DESC")
    Stream<Monster> findAllDesc();

    @Modifying
    @Query("UPDATE Monster m SET m.cutTime = ?1 where m.id = ?2")
    @Transactional
    void updateCutTime(Timestamp cutTime, Long id);

    @Modifying
    @Query(value="UPDATE monster SET gen_time = date_add(cut_time, interval gen_term MINUTE) where id = ?1",nativeQuery=true)
    @Transactional
    void updateGenTime(Long id);

    @Modifying
    @Query(value="UPDATE monster SET max_time = date_add(gen_time, interval random_time MINUTE) where id = ?1",nativeQuery=true)
    @Transactional
    void updateMaxTime(Long id);
}
