package com.baizhi.cmfz_cjw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("com.baizhi.cmfz_cjw.dao")
@ImportResource(locations = {"classpath:yzm.xml"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //文件上传
    @Bean
    public MultipartConfigElement multipartConfigElement(){

        MultipartConfigFactory factory = new MultipartConfigFactory();
        //设置上传的文件大小上限，如果超出限制，就会抛出异常信息
        factory.setMaxFileSize("128KB");
        //设置一次总上传数据的大小，用于多文件上传设置
        factory.setMaxRequestSize("256KB");
        return factory.createMultipartConfig();
    }

}
