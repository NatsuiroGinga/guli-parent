package com.atguigu.oss.utils;

import com.sun.org.apache.xml.internal.security.Init;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
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

    public static String ACCESS_KEY;

    public static String SECRET_KEY;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY = accessKey;
        SECRET_KEY = secretKey;
    }
}
