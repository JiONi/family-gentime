package com.wonida.gentime.Controller;

import com.wonida.gentime.DTO.GenTimeSettingDTO;
import com.wonida.gentime.DTO.MonsterInfoDTO;
import com.wonida.gentime.DTO.MonsterResponseDTO;
import com.wonida.gentime.domain.MemberUser;
import com.wonida.gentime.service.MemberService;
import com.wonida.gentime.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class WebRestController {

    @Autowired
    private MonsterService monsterService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private Environment env;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/saveMonster")
    public void saveMonster(@RequestBody MonsterInfoDTO monsterInfoDTO) {
        monsterService.saveMonster(monsterInfoDTO);
    }

    @PostMapping("/updateGenTime")
    public MonsterResponseDTO updateGenTime(@RequestParam("id") long id, @RequestParam("userId") String userId) {
        memberService.increaseCutCount(userId);
        return monsterService.updateGenTime(id);
    }


    @PostMapping("/updateMonsterMemo")
    public int updateMonsterMemo(@RequestParam("memo") String memo, @RequestParam("id") long id) {
        return monsterService.updateMonsterMemo(memo, id);
    }

    @PostMapping("/clearLostStatus")
    public void clearLostStatus() {
        monsterService.clearLostStatus();
    }

    @PostMapping("/clearLostStatusOne")
    public void clearLostStatusOne(@RequestParam("id") long id) {
        monsterService.clearLostStatusOne(id);
    }

    @PostMapping("/getUserCutCount")
    public List<MemberUser> getUserCutCount() {
        return memberService.getUserCutCount();
    }

    @GetMapping("/profile")
    public String getProfile() {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }
    /*@PostMapping("/saveCutTime")
    public void saveCutTime(@RequestBody )*/
}
