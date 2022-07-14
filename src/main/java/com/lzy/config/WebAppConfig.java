package com.lzy.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.MultipartConfigElement;

//上传配置类
//图片放到/F:/fileUpload/后，从磁盘读取的图片数据scr将会变成images/picturename.jpg的格式
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${cbs.imagesPath}")
    private String mImagesPath;
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize(DataSize.parse("512MB"));
        //设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("512MB"));
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的storage目录下，访问路径如：http://localhost:8081/storage/1.jpg
        //其中image表示访问的前缀。"file:D:/storage/"是文件真实的存储路径
        registry.addResourceHandler("/videos/**").addResourceLocations("file:C:/Users/admin/Desktop/文件夹/大学课程/springboot/springboot-02-web/src/main/resources/static/video/");
        super.addResourceHandlers(registry);
    }

}