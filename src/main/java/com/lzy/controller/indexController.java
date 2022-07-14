package com.lzy.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

//在templates下的所有页面只能通过controller啦跳转！
//需要模板引擎的支持！ thymeleaf
@Controller
public class indexController {
    @RequestMapping({"/index","/"})
    public String index(Model model){
        model.addAttribute("msg","<h1>戚國慶是我兒子</h1>www</br>666");

        //model.addAttribute("users", Arrays.asList("lzy","qgq"));
        return "index";
    }

    @RequestMapping({"/test"})
    public String test(Model model){
        model.addAttribute("msg","<h1>戚國慶是我兒子</h1>www</br>666");

        model.addAttribute("users", Arrays.asList("lzy","qgq"));
        return "test";
    }
}
