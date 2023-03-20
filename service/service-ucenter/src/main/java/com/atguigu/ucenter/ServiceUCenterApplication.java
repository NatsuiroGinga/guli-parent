package com.atguigu.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ginga
 * @since 15/1/2023 上午3:43
 */
@SpringBootApplication
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.ucenter.mapper")
@EnableFeignClients
public class ServiceUCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUCenterApplication.class, args);
    }
}
