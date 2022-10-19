package com.atguigu.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件内容
 * @author ginga
 * @version 1.0
 * @ClassName ConstantPropertiesUtils
 * @Date 12/10/2022 下午10:53
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucketName}")
    private String bucketName;

    @Value("${qiniu.endpoint}")
    private String url;

    public static String ACCESS_KEY;

    public static String SECRET_KEY;

    public static String BUCKET_NAME;

    public static String URL;

    @Override
    public void afterPropertiesSet() {
        ACCESS_KEY = accessKey;
        SECRET_KEY = secretKey;
        BUCKET_NAME = bucketName;
        URL = url;
    }
}
