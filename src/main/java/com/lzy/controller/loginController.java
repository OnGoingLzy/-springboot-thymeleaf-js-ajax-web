package com.lzy.controller;


import com.lzy.Dao.ShiPinDao;
import com.lzy.Dao.TouXiangDao;
import com.lzy.Model.accounts;
import com.lzy.Dao.accountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class loginController {
    @Autowired
    TouXiangDao touXiangDao;

    @RequestMapping("/login")
    public String tologin(){
            return "login2";
    }

    @RequestMapping("/register")
    public String toregister(){
        return "register";
    }
    @RequestMapping("/uploadtest")
    public String uploadtest(){
        return "uploadtest";
    }

    @RequestMapping("/userLogin")
    public String loginDianji(@RequestParam("account") String account, @RequestParam("password") String pwd, Model model, Model name, HttpSession session) throws SQLException{
        List<accounts> list = new ArrayList<>();
        accountDao accountDao = new accountDao();
            list = accountDao.accountList();
        for (int i = 0;i<list.size();i++){
            System.out.println("查询账户:"+list.get(i).getAccount());
            if(list.get(i).getAccount().equals(account) && list.get(i).getPwd().equals(pwd)){
                System.out.println("登录成功");
                name.addAttribute("userName", list.get(i).getName());
                System.out.println(list.get(i).getName());
                session.setAttribute("userName2",list.get(i).getName());
                session.setAttribute("userAccount", list.get(i).getAccount());
                session.setAttribute("userTouxiang",touXiangDao.selectTouxiang(list.get(i).getAccount()));
                System.out.println("登录------当前头像"+touXiangDao.selectTouxiang(list.get(i).getAccount()));
                session.setAttribute("oldTouxiang", touXiangDao.selectTouxiang(list.get(i).getAccount()));
                return "index";
                //return "redirect:successlogin";//重定向  会导致端口映射的网页无法直接跳转到successlogin
            }
        }
        model.addAttribute("msg","用户名或者密码错误");

        return "login2";
    }

    //注销登录
    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        System.out.println("logout");
        //session失效

        session.removeAttribute("userName2");
        session.removeAttribute("userAccount");
        session.removeAttribute("userTouxiang");
        session.removeAttribute("");
        System.out.println("退出者的旧头像"+session.getAttribute("oldTouxiang"));
        session.removeAttribute("oldTouxiang");
        return "index";
    }


}
