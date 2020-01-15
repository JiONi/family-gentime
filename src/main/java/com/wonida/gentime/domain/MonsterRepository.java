package com.wonida.gentime.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.stream.Stream;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    @Query("SELECT m " +
            "FROM Monster m " +
            "ORDER BY m.genTime")
    Stream<Monster> findAllDesc();

    @Query("SELECT m " +
            "FROM Monster m " +
            "WHERE m.mobGroup = ?1 " +
            "ORDER BY m.genTerm, m.genTime")
    Stream<Monster> findAllByMobGroup(int mobGroup);

    @Query("SELECT m " +
            "FROM Monster m " +
            "WHERE m.genTerm between (?1-10) and ?1 " +
            "ORDER BY m.genTime")
    Stream<Monster> findAllByGenTerm(int genTerm);

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

    @Modifying
    @Query("UPDATE Monster m SET m.memo = ?1 WHERE m.id = ?2")
    @Transactional
    int updateMonsterMemo(String memo, Long id);
}
