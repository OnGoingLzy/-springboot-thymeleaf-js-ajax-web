package com.lzy.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登陆成功之后，应该有用户的session

        HttpSession session = request.getSession();
        String loginUser = (String) session.getAttribute("userName2");
        System.out.println("登录拦截器获取用户:"+loginUser);//attribute取不到
        if(loginUser==null){//没有登录
            System.out.println("拦截器拦截:登录失败");
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/login").forward(request,response);
            return false;
        }else{
           // request.setAttribute("userName", loginUser);
            return  true;
        }
    }
}
