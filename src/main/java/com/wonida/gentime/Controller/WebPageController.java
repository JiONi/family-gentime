package com.wonida.gentime.Controller;

import com.wonida.gentime.service.MemberService;
import com.wonida.gentime.service.MonsterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class WebPageController {
    @Autowired
    private MonsterService monsterService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/{key}")
    public String main(@PathVariable String key,  Model model){
        if(memberService.getMemberByUserId(key) == null){
            return "error";
        }else{
            memberService.increaseAccessCount(key);
            model.addAttribute("key", key);
            model.addAttribute("monsters", monsterService.findAllDesc());
            return "main";
        }
    }

    @GetMapping("/{key}/genTermList")
    public String genTerm3hour(@PathVariable String key, @RequestParam("genTerm") int genTerm, Model model){
        if(memberService.getMemberByUserId(key) == null){
            return "error";
        }else{
            memberService.increaseAccessCount(key);
            model.addAttribute("key",key);
            model.addAttribute("monsters", monsterService.findAllByGenTerm(genTerm));
            model.addAttribute("genTerm", genTerm);
            return "main";
        }
    }

    @GetMapping("/adminPage")
    public String adminPage(){
        return "adminPage";
    }
}
