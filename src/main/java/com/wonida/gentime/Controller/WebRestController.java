package com.wonida.gentime.Controller;
import com.wonida.gentime.DTO.GenTimeSettingDTO;
import com.wonida.gentime.DTO.MonsterInfoDTO;
import com.wonida.gentime.DTO.MonsterResponseDTO;
import com.wonida.gentime.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
public class WebRestController {

    @Autowired
    private MonsterService monsterService;
    @Autowired
    private Environment env;

    @GetMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    @PostMapping("/saveMonster")
    public void saveMonster(@RequestBody MonsterInfoDTO monsterInfoDTO){

        monsterService.saveMonster(monsterInfoDTO);
    }

    @PostMapping("/updateGenTime")
    public MonsterResponseDTO updateGenTime(@RequestParam("id") long id){
        return monsterService.updateGenTime(id);

    }

    @GetMapping("/profile")
    public String getProfile () {
        return Arrays.stream(env.getActiveProfiles())
                .findFirst()
                .orElse("");
    }
    /*@PostMapping("/saveCutTime")
    public void saveCutTime(@RequestBody )*/
}
