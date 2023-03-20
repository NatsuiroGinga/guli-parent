package com.atguigu.front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ginga
 * @since 14/1/2023 下午4:22
 */
@SpringBootApplication
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.front.mapper")
@EnableFeignClients
public class ServiceFrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceFrontApplication.class, args);
    }
}
