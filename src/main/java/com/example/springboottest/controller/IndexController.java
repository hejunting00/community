package com.example.springboottest.controller;

import com.example.springboottest.dto.QuestionDTO;
import com.example.springboottest.mapper.UserMapper;
import com.example.springboottest.model.User;
import com.example.springboottest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;
    
    @GetMapping(value = {"/","/index"})
    public String index(HttpServletRequest request,
                        Model model
                        ){
        Cookie[] cookies = request.getCookies();
        if(cookies !=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionslist=questionService.list();
        model.addAttribute("question",questionslist);
        return "index";
    }
}
