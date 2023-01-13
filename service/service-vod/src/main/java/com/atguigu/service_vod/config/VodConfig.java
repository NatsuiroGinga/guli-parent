package com.atguigu.service_vod.config;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.atguigu.service_vod.util.VODProperties.*;

/**
 * @author ginga
 * @since 12/1/2023 下午8:54
 */
@Configuration
public class VodConfig {

    @Bean
    public IAcsClient client() {
        final DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        return new DefaultAcsClient(profile);
    }

}
