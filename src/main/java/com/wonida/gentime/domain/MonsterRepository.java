package com.wonida.gentime.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    @Modifying
    @Query("UPDATE Monster m SET m.cutTime = ?1 where m.id = ?2")
    @Transactional
    void updateCutTime(Timestamp cutTime, Long id);

    @Modifying
    @Query("UPDATE Monster m SET m.genTime = (m.cutTime+m.genTerm) where m.id = ?1")
    @Transactional
    void updateGenTime(Long id);

}
