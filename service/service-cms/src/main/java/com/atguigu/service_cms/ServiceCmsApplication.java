package com.atguigu.service_cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ginga
 * @since 14/1/2023 下午2:29
 */
@SpringBootApplication
@ComponentScan("com.atguigu")
@MapperScan("com.atguigu.service_cms.mapper")
public class ServiceCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApplication.class, args);
    }
}
