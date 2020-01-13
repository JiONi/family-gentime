package com.wonida.gentime.DTO;

import com.wonida.gentime.domain.Monster;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
public class MonsterResponseDTO {
    private Long id;
    private String name;
    private String location;
    private Timestamp genTime;
    private String genTimeStr;
    private Timestamp maxTime;

    public MonsterResponseDTO(Monster entity){
        id = entity.getId();
        name = entity.getName();
        location = entity.getLocation();
        genTime = entity.getGenTime();
        genTimeStr = toStringDateTime(entity.getGenTime().toLocalDateTime());
        maxTime = entity.getMaxTime();
    }

    private String toStringDateTime(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }

}
