package com.wonida.gentime.DTO;

import com.wonida.gentime.domain.Monster;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
public class MonsterInfoDTO {
    private String name;
    private String location;
    private String cutTime;
    private int genTerm;
    private int randomTime;
    private boolean mobType;
    private int mobGroup;
    private String memo;

    public Monster toEntity() {
        return Monster.builder()
                .name(name)
                .location(location)
                .cutTime(cutTime)
                .genTerm(genTerm)
                .randomTime(randomTime)
                .mobType(mobType)
                .mobGroup(mobGroup)
                .memo(memo)
                .build();
    }
}
