package com.lzy.config;
//扩展mvc


import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//如果更新springmvc，官方建议这样做
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main.html").setViewName("index");
        registry.addViewController("/successlogin").setViewName("/index");
    }

    @Override//拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        //需要重启项目才能更新
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/registerDianji","/userLogin","/login","/registerDianji","/logout","/","/register/**","/font/**","/css/**","/picture/**","/video/**","/js/**","/image/**","/static/**");
    }
}
