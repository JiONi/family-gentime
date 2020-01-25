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
    public void saveMonster(MonsterInfoDTO monsterInfoDTO){
        monsterRepository.save(monsterInfoDTO.toEntity()).getId();
        monsterRepository.updateGenTime(monsterInfoDTO.toEntity().getId());
        monsterRepository.updateMaxTime(monsterInfoDTO.toEntity().getId());
    }

    @Transactional(readOnly = true)
    public List<MonsterResponseDTO> findAll() {
        return monsterRepository.findAll().stream().map(MonsterResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MonsterResponseDTO> findAllDesc() {
        return monsterRepository.findAllDesc().map(MonsterResponseDTO::new).collect(Collectors.toList());
    }

    /*@Transactional(readOnly = true)
    public List<MonsterResponseDTO> findAllByMobGroup(int mobGroup){
        if(mobGroup == 0){
            return monsterRepository.findAllDesc().map(MonsterResponseDTO::new).collect(Collectors.toList());
        }else{
            return monsterRepository.findAllByMobGroup(mobGroup).map(MonsterResponseDTO::new).collect(Collectors.toList());
        }
    }*/

    @Transactional(readOnly = true)
    public List<MonsterResponseDTO> findAllByGenTerm(int genTerm){
        return monsterRepository.findAllByGenTerm(genTerm).map(MonsterResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MonsterResponseDTO> findAllByMobGroup(int runatra, int silunas, boolean type){
        return monsterRepository.findAllByMobGroup(runatra, silunas, type).map(MonsterResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public MonsterResponseDTO updateGenTime(Long id){
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp cutTime = Timestamp.valueOf(localDateTime);
        monsterRepository.updateCutTime(cutTime, id);
        monsterRepository.updateGenTime(id);
        monsterRepository.updateMaxTime(id);
        MonsterResponseDTO result = new MonsterResponseDTO(monsterRepository.findById(id).get());
        return result;
    }

    @Transactional
    public int updateMonsterMemo(String memo, Long id){
        return monsterRepository.updateMonsterMemo(memo, id);
    }
}
