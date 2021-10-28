package com.example.springboottest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping(value = "/hello")
    public String Hello(@RequestParam(name="name") String name, Model model){
        model.addAttribute("name",name);
        return  "hello";
    }
}
