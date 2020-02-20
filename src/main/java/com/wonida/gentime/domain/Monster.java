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

    @Column
    private int randomTime;

    @Column(nullable = false, columnDefinition = "tinyint default 1")
    private boolean mobType;

    @Column
    private int mobGroup;

    @Column(columnDefinition = "integer default 0")
    private int channel;

    @Column(length = 200)
    private String memo;

    @Column(nullable = false, columnDefinition = "tinyint default 0")
    private boolean eventMob;

    @Builder
    public Monster(String name, String location, int genTerm, int randomTime, boolean mobType, String cutTime, int mobGroup,String memo){
        this.name = name;
        this.location = location;
        this.genTerm = genTerm;
        this.randomTime = randomTime;
        this.mobType = mobType;
        this.cutTime = toTimeStampString(cutTime);
        this.mobGroup = mobGroup;
        this.memo = memo;
    }

    public Monster(Timestamp cutTime){
        this.cutTime = cutTime;
    }

    private Timestamp toTimeStampString(String localDateTime){
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }
}
