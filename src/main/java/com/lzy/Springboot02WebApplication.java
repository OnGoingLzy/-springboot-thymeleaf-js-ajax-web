package com.lzy;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot02WebApplication {
//数据库不打开就无法启动 entityxxx?
    public static void main(String[] args) {
        SpringApplication.run(Springboot02WebApplication.class, args);
    }

}
