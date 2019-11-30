package com.cc.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by xiaomi on 2019/11/30.
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
