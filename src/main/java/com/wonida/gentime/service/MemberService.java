package com.wonida.gentime.service;

import com.wonida.gentime.domain.MemberRepository;
import com.wonida.gentime.domain.MemberUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberUser getMemberByUserId(String userId) {
        MemberUser memberUser = memberRepository.findByUserIdAndUseYn(userId, true);
        if(memberUser == null){
            return null;
        }else{
            return memberUser;
        }
    }

    public void increaseAccessCount(String userId){
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp accessTime = Timestamp.valueOf(localDateTime);
        memberRepository.updateLastAccessTime(accessTime,userId);
        memberRepository.increaseAccessCount(userId);
    }

    public void increaseCutCount(String userId){
        memberRepository.increaseCutCount(userId);
    }

    public List<MemberUser> getMemberAll(){
        return memberRepository.findAllOrOrderByLastAccessTime();
    }

    public List<MemberUser> getUserCutCount(){
        return memberRepository.findUserCutCount();
    }
}
