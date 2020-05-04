package com.wonida.gentime.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

public interface MemberRepository extends JpaRepository<MemberUser, String>, CrudRepository<MemberUser, String> {
    @Modifying
    @Query("UPDATE MemberUser u SET u.accessCount = u.accessCount+1 where u.userId = ?1")
    @Transactional
    void increaseAccessCount(String id);

    @Modifying
    @Query("UPDATE MemberUser u SET u.lastAccessTime = ?1 where u.userId = ?2")
    @Transactional
    void updateLastAccessTime(Timestamp accessTime, String id);

    @Query("SELECT u FROM MemberUser u ORDER BY u.lastAccessTime desc")
    List<MemberUser> findAllOrOrderByLastAccessTime();

    @Modifying
    @Query("UPDATE MemberUser u SET u.cutCount = u.cutCount+1 where u.userId = ?1")
    @Transactional
    void increaseCutCount(String id);

    @Query("SELECT u FROM MemberUser u ORDER BY u.cutCount DESC")
    List<MemberUser> findUserCutCount();

    MemberUser findByUserIdAndUseYn(String userId, boolean useYn);
}
