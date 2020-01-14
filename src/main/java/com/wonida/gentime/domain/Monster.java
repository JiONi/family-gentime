package com.wonida.gentime.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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

    @Column(columnDefinition = "default false")
    private boolean mobType;

    @Column
    private int mobGroup;

    @Builder
    public Monster(String name, String location, int genTerm, int randomTime, boolean mobType, String cutTime, int mobGroup){
        this.name = name;
        this.location = location;
        this.genTerm = genTerm;
        this.randomTime = randomTime;
        this.mobType = mobType;
        this.cutTime = toTimeStampString(cutTime);
        this.mobGroup = mobGroup;
    }

    public Monster(Timestamp cutTime){
        this.cutTime = cutTime;
    }

    private Timestamp toTimeStampString(String localDateTime){
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        return timestamp;
    }
}
