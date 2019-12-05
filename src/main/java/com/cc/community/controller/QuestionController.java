package com.cc.community.controller;

import com.cc.community.dto.QuestionDTO;
import com.cc.community.mapper.QuestionMapper;
import com.cc.community.model.User;
import com.cc.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaomi on 2019/12/3.
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           HttpServletRequest request,
                           Model model){
        QuestionDTO questionDTO=questionService.getById(id);
        //如果阅读者和发布人不一样，则增加阅读数
        User user = (User) request.getSession().getAttribute("user");
        if (user.getId()!=(long) questionDTO.getCreator()){
            questionService.incViewer(id);
            questionDTO=questionService.getById(id);
        }
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
