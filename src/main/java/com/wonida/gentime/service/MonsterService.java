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
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MonsterService {
    @Autowired
    private MonsterRepository monsterRepository;

    @Transactional
    public void saveMonster(MonsterInfoDTO monsterInfoDTO) {
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
    public List<MonsterResponseDTO> findAllByGenTerm(int genTerm) {
        return monsterRepository.findAllByGenTerm(genTerm).map(MonsterResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MonsterResponseDTO> findAllByMobGroup(int runatra, int silunas, boolean type) {
        return monsterRepository.findAllByMobGroup(runatra, silunas, type).map(MonsterResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MonsterResponseDTO> findAllByMobGroupGeneral(int mobGroup, boolean type) {
        return monsterRepository.findAllByMobGroupGeneral(mobGroup, type).map(MonsterResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MonsterResponseDTO> findAllByEventMob() {
        return monsterRepository.findAllByEventMob().map(MonsterResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public MonsterResponseDTO updateGenTime(Long id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp cutTime = Timestamp.valueOf(localDateTime);
        monsterRepository.updateCutTime(cutTime, id);
        monsterRepository.updateGenTime(id);
        monsterRepository.updateMaxTime(id);
        monsterRepository.updateLostCount(0, id);
        monsterRepository.updateMonsterMemo("", id);
        MonsterResponseDTO result = new MonsterResponseDTO(monsterRepository.findById(id).get());
        return result;
    }

    @Transactional
    public int updateMonsterMemo(String memo, Long id) {
        return monsterRepository.updateMonsterMemo(memo, id);
    }

    @Transactional
    public void clearLostStatus() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp now = Timestamp.valueOf(localDateTime);

        while (1 == 1) {
            List<MonsterResponseDTO> result = monsterRepository.findAllByMaxTimeBeforeAndMobTypeEquals(now, true).map(MonsterResponseDTO::new).collect(Collectors.toList());
            if (result == null || result.size() < 1) {
                break;
            } else {
                for (int i = 0; i < result.size(); i++) {
                    MonsterResponseDTO monster = result.get(i);
                    monsterRepository.autoCuttimeUpdate(monster.getId());
                    monsterRepository.updateGenTime(monster.getId());
                    monsterRepository.updateMaxTime(monster.getId());
                    monsterRepository.updateMonsterMemo((monster.getLostCount() + 1) + " 타임 놓침", monster.getId());
                    monsterRepository.updateLostCount(monster.getLostCount() + 1, monster.getId());
                }
            }
        }
    }

    @Transactional
    public void clearLostStatusOne(long id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp now = Timestamp.valueOf(localDateTime);

        while (1 == 1) {
            Monster monster = monsterRepository.findByIdAndMaxTimeBefore(id, now);
            if (monster == null || monster.getId() == null) {
                break;
            } else {
                monsterRepository.autoCuttimeUpdate(monster.getId());
                monsterRepository.updateGenTime(monster.getId());
                monsterRepository.updateMaxTime(monster.getId());
                monsterRepository.updateMonsterMemoLostMob(monster.getId());
                monsterRepository.updateLostCountAuto(monster.getId());
            }
        }
    }
}
