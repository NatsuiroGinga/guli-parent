package com.atguigu.service_edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ginga
 * @version 1.0
 * @ClassName ServiceEduApplication
 * @Date 15/9/2022 下午5:10
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu")
@MapperScan("com.atguigu.service_edu.mapper")
@EnableFeignClients
public class ServiceEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class, args);
    }

}
