package com.wonida.gentime.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class GenTimeSettingDTO {

    private Long id;
    private String cutTime;
}
