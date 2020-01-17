package com.wonida.gentime.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface MemberRepository extends JpaRepository<MemberUser,String>, CrudRepository<MemberUser,String> {
    @Modifying
    @Query("UPDATE MemberUser u SET u.accessCount = u.accessCount+1 where u.userId = ?1")
    @Transactional
    void increaseAccessCount(String id);
}
