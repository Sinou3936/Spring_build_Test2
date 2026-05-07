package com.back.spring_build_test2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainApplication {

    @GetMapping("/")
    public String index(){

        return "redirect:/article/list";
    }
}
