package com.atguigu.service_vod;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

/**
 * @author ginga
 * @since 12/1/2023 下午8:07
 */
@Slf4j
public class Auth {
    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai",
                                                           "LTAI5t6K96CgdMQZS4t4QJDU",
                                                           "U8CPIkyeqRaLKHmpbgNLIAFNHGnUAc");
        IAcsClient client = new DefaultAcsClient(profile);
        final GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("a05cdbc091bd71ed87836733a78e0102");
        request.setSysProtocol(ProtocolType.HTTPS);

        try {
            final GetVideoPlayAuthResponse authResponse = client.getAcsResponse(request);
            Assert.notNull(authResponse, "response must not be null!");
            log.info("auth: {}", authResponse.getPlayAuth());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
