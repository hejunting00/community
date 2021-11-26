package com.example.springboottest.controller;

import com.example.springboottest.dto.PaginationDTO;
import com.example.springboottest.mapper.UserMapper;
import com.example.springboottest.model.User;
import com.example.springboottest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = {"/profile/{action}"})
    public String profile(
            @PathVariable(name="action") String action,
            HttpServletRequest request,
            Model model,
            @RequestParam(name="page", defaultValue="1") Integer page,
            @RequestParam(name="size", defaultValue="5") Integer size
    ){

        User user=(User) request.getSession().getAttribute("user");
        if(user==null)
        {
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
