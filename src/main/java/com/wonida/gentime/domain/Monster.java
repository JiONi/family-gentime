package com.wonida.gentime.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Monster {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String location;

    @Column
    private Timestamp genTime;

    @Column
    private Timestamp cutTime;

    @Column
    private Timestamp maxTime;

    @Column
    private int genTerm;

    @Builder
    public Monster(String name, String location, int genTerm){
        this.name = name;
        this.location = location;
        this.genTerm = genTerm;
    }

    public Monster(Timestamp cutTime){
        this.cutTime = cutTime;
    }
}
