package com.lzy.controller;

import com.lzy.Dao.ShiPinDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FileUploadController {

    //访问路径为：http://localhost:80/file
    @Autowired
    ShiPinDao shiPinDao;
    @RequestMapping("/file")

    public String file(Model model1){
        System.out.print("================请求路径===跳转视频页面====="+"\n");
        List<String> tagall = shiPinDao.selectAllTag();
        model1.addAttribute("tagall",tagall);
        return "/file";

    }

    @RequestMapping("/touxiang")
    public String touxaing(){
        System.out.println("====跳转上传头像页面");
        return "/touxiang";
    }

//    @RequestMapping("/shangchuan")
//
//    public String shangchuan(){
//        System.out.print("================请求路径===跳转index页面====="+"\n");
//        return "/index";
//
//    }
}

