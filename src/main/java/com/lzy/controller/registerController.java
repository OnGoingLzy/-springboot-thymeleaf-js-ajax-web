package com.lzy.controller;

import com.lzy.Model.accounts;
import com.lzy.Dao.accountDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class registerController {
    @RequestMapping("/registerDianji")
    public String registerClick(@RequestParam("raccount") String racc,
                                @RequestParam("rpassword") String rpwd,
                                @RequestParam("rname") String rname, Model model){
        List<accounts> list = new ArrayList<>();
        accountDao accountDao = new accountDao();
        try{
            list = accountDao.accountList();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(racc.isEmpty()){
            model.addAttribute("msg","不能为空!");
            return "register";
        }
        for(accounts accounts : list){
            if(accounts.getAccount().equals(racc)){
                model.addAttribute("msg","该用户名已被注册!");
                return "register";
            }
        }
        String sss = "insert into accountTable(account,pwd,name) values (\""+racc+"\",\""+rpwd+"\",\""+rname+"\")";
        System.out.println(sss);
        try {
            accountDao.addAccount("insert into accountTable(account,pwd,name) values (\""+racc+"\",\""+rpwd+"\",\""+rname+"\")");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        model.addAttribute("regsuccess","注册成功!");
        System.out.println("注册成功");
        return "register";
        //return "redirect:/register";//重定向
    }
}
