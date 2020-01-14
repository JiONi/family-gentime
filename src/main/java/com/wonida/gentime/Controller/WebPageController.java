package com.wonida.gentime.Controller;

import com.wonida.gentime.service.MonsterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class WebPageController {
    @Autowired
    private MonsterService monsterService;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("monsters", monsterService.findAllDesc());
        return "main";
    }

    @GetMapping("/genTermList")
    public String genTerm3hour(@RequestParam("genTerm") int genTerm, Model model){
        model.addAttribute("monsters", monsterService.findAllByGenTerm(genTerm));
        model.addAttribute("genTerm", genTerm);
        return "main";
    }

    @GetMapping("/adminPage")
    public String adminPage(){
        return "adminPage";
    }
}
