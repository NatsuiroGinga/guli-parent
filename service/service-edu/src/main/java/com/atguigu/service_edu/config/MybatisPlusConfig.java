package com.atguigu.service_edu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ginga
 * @version 1.0
 * @ClassName MybatisPlusConfig
 * @Date 15/9/2022 下午5:11
 */
@Configuration
@MapperScan("com.atguigu.service_edu.mapper")
public class MybatisPlusConfig {
}
