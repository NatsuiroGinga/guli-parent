package com.atguigu.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ginga
 * @version 1.0
 * @ClassName ServiceOSSApplication
 * @Date 12/10/2022 上午11:18
 */
// 忽略数据源自动配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.atguigu"})
public class ServiceOSSApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOSSApplication.class, args);
    }
}
