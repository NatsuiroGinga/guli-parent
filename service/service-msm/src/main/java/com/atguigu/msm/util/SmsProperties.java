package com.atguigu.msm.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author ginga
 * @since 15/1/2023 上午2:31
 */
@Component
@PropertySource(value = "classpath:sms.yml", encoding = "utf-8")
@ConfigurationProperties(prefix = "sms")
public class SmsProperties implements InitializingBean {

    public static String ACCESS_KEY_ID;

    public static String ACCESS_KEY_SECRET;

    public static String REGION;

    public static String SIGN_NAME;

    public static String TEMPLATE_CODE;

    public static String ENDPOINT;

    @Value("${id}")
    private String accessKeyId;

    @Value("${secret}")
    private String accessKeySecret;

    @Value("${region}")
    private String region;

    @Value("${sign-name}")
    private String signName;

    @Value("${template-code}")
    private String templateCode;

    @Value("${endpoint}")
    private String endpoint;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        REGION = region;
        SIGN_NAME = signName;
        TEMPLATE_CODE = templateCode;
        ENDPOINT = endpoint;
    }
}
