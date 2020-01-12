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
    private Time genTerm;

    public Monster toEntity(){
        return Monster.builder()
                .name(name)
                .location(location)
                .genTerm(genTerm)
                .build();
    }
}
