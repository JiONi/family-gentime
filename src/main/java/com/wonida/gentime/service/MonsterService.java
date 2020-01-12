package com.wonida.gentime.service;

import com.wonida.gentime.DTO.GenTimeSettingDTO;
import com.wonida.gentime.DTO.MonsterInfoDTO;
import com.wonida.gentime.DTO.MonsterResponseDTO;
import com.wonida.gentime.domain.Monster;
import com.wonida.gentime.domain.MonsterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MonsterService {
    @Autowired
    private MonsterRepository monsterRepository;

    @Transactional
    public Long saveMonster(MonsterInfoDTO monsterInfoDTO){
        return monsterRepository.save(monsterInfoDTO.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<MonsterResponseDTO> findAll() {
        return monsterRepository.findAll().stream().map(MonsterResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public MonsterResponseDTO updateGenTime(Long id){
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp cutTime = Timestamp.valueOf(localDateTime);
        monsterRepository.updateCutTime(cutTime, id);
        monsterRepository.updateGenTime(id);
        MonsterResponseDTO result = new MonsterResponseDTO(monsterRepository.findById(id).get());
        return result;
    }
}
