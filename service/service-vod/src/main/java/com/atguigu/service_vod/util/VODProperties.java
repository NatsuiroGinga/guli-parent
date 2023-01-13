package com.atguigu.service_vod.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 阿里云VOD服务配置参数类
 *
 * @author ginga
 * @since 12/1/2023 下午8:43
 */
@Component
@PropertySource("classpath:aliyun-vod.yml")
@ConfigurationProperties(prefix = "vod")
public class VODProperties implements InitializingBean {

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String REGION_ID;

    @Value("${id}")
    private String accessKeyId;
    @Value("${secret}")
    private String accessKeySecret;
    @Value("${region-id}")
    private String regionId;

    @Override
    public void afterPropertiesSet() {
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        REGION_ID = regionId;
    }
}
