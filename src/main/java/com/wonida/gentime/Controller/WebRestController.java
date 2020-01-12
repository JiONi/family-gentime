package com.wonida.gentime.Controller;
import com.wonida.gentime.DTO.GenTimeSettingDTO;
import com.wonida.gentime.DTO.MonsterInfoDTO;
import com.wonida.gentime.DTO.MonsterResponseDTO;
import com.wonida.gentime.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebRestController {

    @Autowired
    private MonsterService monsterService;

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
    /*@PostMapping("/saveCutTime")
    public void saveCutTime(@RequestBody )*/
}
