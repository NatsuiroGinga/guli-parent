package com.atguigu.statistic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ginga
 * @since 20/1/2023 下午1:03
 */
@SpringBootApplication(scanBasePackages = "com.atguigu")
@EnableFeignClients
@MapperScan("com.atguigu.statistic.mapper")
@EnableScheduling
public class ServiceStatisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceStatisticApplication.class, args);
    }
}
