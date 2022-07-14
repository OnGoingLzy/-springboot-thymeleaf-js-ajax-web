package com.lzy.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


@Controller
public class uploadTestController {
    private static final String TMP_PATH = "C:/Users/admin/Desktop/文件夹/大学课程/springboot/springboot-02-web/src/main/resources/static/shenHeVideo/";


    //@RequestMapping(value = "/upload", method = RequestMethod.POST)
    @RequestMapping(value = "/upload")
//    @ResponseBody
    public String fileUpload(@RequestParam("uploadFile") MultipartFile file, Model model, HttpSession session) {
        System.out.println("正在上传到服务器审核队列");
        if (file.isEmpty()) {

            return "空";
        }

        try {
            File tmp = new File(TMP_PATH, file.getOriginalFilename());
            if(!tmp.getParentFile().exists()){
                tmp.getParentFile().mkdirs();
            }
            file.transferTo(tmp);


        } catch (IOException e) {

        }
        model.addAttribute("msg2", "文件上传成功!等待审核");
        System.out.println(file.getOriginalFilename()+"文件上传成功!等待审核");
        session.setAttribute("fileName", file.getOriginalFilename());
        return "/file";
    }
}