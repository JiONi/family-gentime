package com.wonida.gentime.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Cacheable;
import java.sql.Timestamp;
import java.util.stream.Stream;

public interface MonsterRepository extends JpaRepository<Monster, Long> {

    @Query("SELECT m " +
            "FROM Monster m " +
            "WHERE m.mobType = true AND m.hideYn = false " +
            "ORDER BY m.genTime")
    Stream<Monster> findAllDesc();

    @Query("SELECT m " +
            "FROM Monster m " +
            "WHERE m.genTerm between (?1-10) and (?1+40)" +
            "ORDER BY m.genTime")
    Stream<Monster> findAllByGenTerm(int genTerm);

    @Query("SELECT m  FROM Monster  m WHERE m.mobGroup in (?1, ?2) AND m.mobType = ?3 AND m.hideYn = false ORDER BY m.genTime")
    Stream<Monster> findAllByMobGroup(int runatra, int silunas, boolean type);

    @Query("SELECT m " +
            "FROM Monster m " +
            "WHERE m.mobGroup = ?1 AND m.mobType = ?2 AND m.eventMob = false AND m.hideYn = false ORDER BY m.genTime")
    Stream<Monster> findAllByMobGroupGeneral(int mobGroup, boolean type);

    @Query("SELECT m " +
            "FROM Monster  m " +
            "WHERE m.eventMob = true AND m.hideYn = false ORDER BY m.genTime")
    Stream<Monster> findAllByEventMob();

    @Modifying
    @Query("UPDATE Monster m SET m.cutTime = ?1 where m.id = ?2")
    @Transactional
    void updateCutTime(Timestamp cutTime, Long id);

    @Modifying
    @Query(value = "UPDATE monster SET gen_time = date_add(cut_time, interval gen_term MINUTE) where id = ?1", nativeQuery = true)
    @Transactional
    void updateGenTime(Long id);

    @Modifying
    @Query(value = "UPDATE monster SET max_time = date_add(gen_time, interval random_time MINUTE) where id = ?1", nativeQuery = true)
    @Transactional
    void updateMaxTime(Long id);

    @Modifying
    @Query("UPDATE Monster m SET m.memo = ?1 WHERE m.id = ?2")
    @Transactional
    int updateMonsterMemo(String memo, Long id);

    @Modifying
    @Query("UPDATE Monster m SET m.memo = concat(m.lostCount, ' 타임 놓침') WHERE m.id = ?1")
    @Transactional
    int updateMonsterMemoLostMob(Long id);

    Stream<Monster> findAllByMaxTimeBeforeAndMobTypeEquals(Timestamp now, boolean mobType);

    @CachePut(key = "#id")
    Monster findByIdAndMaxTimeBefore(long id, Timestamp now);

    @Modifying
    @Query(value = "UPDATE monster SET cut_time = gen_time where id = ?1", nativeQuery = true)
    @Transactional
    void autoCuttimeUpdate(long id);

    @Modifying
    @Query(value = "UPDATE monster SET lost_count = ?1 where id = ?2 AND mob_type = 1", nativeQuery = true)
    @Transactional
    void updateLostCount(int lostCount, Long id);

    @Modifying
    @Query(value = "UPDATE monster SET lost_count = lost_count+1 where id = ?1 AND mob_type = 1", nativeQuery = true)
    @Transactional
    void updateLostCountAuto(Long id);

}
