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
            "WHERE m.mobType = true " +
            "ORDER BY m.genTime")
    Stream<Monster> findAllDesc();

    @Query("SELECT m " +
            "FROM Monster m " +
            "WHERE m.genTerm between (?1-10) and (?1+40) " +
            "ORDER BY m.genTime")
    Stream<Monster> findAllByGenTerm(int genTerm);

    @Query("SELECT m  FROM Monster  m WHERE m.mobGroup in (?1, ?2) AND m.mobType = ?3 ORDER BY m.genTime")
    Stream<Monster> findAllByMobGroup(int runatra, int silunas, boolean type);

    @Query("SELECT m "+
            "FROM Monster m " +
            "WHERE m.mobGroup = ?1 AND m.mobType = ?2 AND m.eventMob = false ORDER BY m.genTime")
    Stream<Monster> findAllByMobGroupGeneral(int mobGroup, boolean type);

    @Query("SELECT m "+
            "FROM Monster  m " +
            "WHERE m.eventMob = true ORDER BY m.genTime")
    Stream<Monster> findAllByEventMob();

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

    Stream<Monster> findAllByMaxTimeBeforeAndMobTypeEquals(Timestamp now, boolean mobType);

    @Modifying
    @Query(value="UPDATE monster SET cut_time = gen_time where max_time < ?1 AND mob_type = 1",nativeQuery=true)
    @Transactional
    void autoCuttimeUpdate(Timestamp now);

    @Modifying
    @Query(value="UPDATE monster SET lost_count = ?1 where id = ?2 AND mob_type = 1",nativeQuery=true)
    @Transactional
    void updateLostCount(int lostCount, Long id);

    @Modifying
    @Query(value="UPDATE Monster m SET m.memo = ?1 where m.id = ?2 AND m.mobType = 1")
    @Transactional
    void updateMemo(String memo, Long id);
}
