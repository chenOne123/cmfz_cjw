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


}
