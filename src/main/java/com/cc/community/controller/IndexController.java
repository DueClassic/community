package com.cc.community.controller;

import com.cc.community.dto.PageDTO;
import com.cc.community.dto.QuestionDTO;
import com.cc.community.mapper.QuestionMapper;
import com.cc.community.mapper.UserMapper;
import com.cc.community.model.Question;
import com.cc.community.model.User;
import com.cc.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xiaomi on 2019/11/30.
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "4") Integer size,
                        @RequestParam(name = "search",required = false) String search){
        PageDTO pageDTO=questionService.list(search,page,size);
        model.addAttribute("pagination",pageDTO);
        model.addAttribute("search",search);
        return "index";
    }
}
