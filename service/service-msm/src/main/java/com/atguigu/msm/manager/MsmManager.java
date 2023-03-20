package com.atguigu.msm.manager;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponseBody;
import com.atguigu.common_utils.ErrorInfo;
import com.atguigu.common_utils.Jackson;
import com.atguigu.msm.util.SmsProperties;
import com.atguigu.msm.vo.SMSVo;
import com.atguigu.service_base.exceptionhandler.exception.GuliException;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ginga
 * @since 15/1/2023 上午1:29
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MsmManager {

    private final Jackson jackson;

    /**
     * 发送短信
     *
     * @param phone 手机号
     * @param smsVo 验证码类
     */
    @Async
    public void sendSMS(String phone, SMSVo smsVo) {
        Assert.hasText(phone, "手机号不能为空");

        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                                                                                      .accessKeyId(SmsProperties.ACCESS_KEY_ID)
                                                                                      .accessKeySecret(SmsProperties.ACCESS_KEY_SECRET)
                                                                                      .build());

        AsyncClient client = AsyncClient.builder()
                                        .region(SmsProperties.REGION) // Region ID
                                        //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                                        .credentialsProvider(provider)
                                        //.serviceConfiguration(Configuration.create()) // Service-level configuration
                                        // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                                        .overrideConfiguration(
                                                ClientOverrideConfiguration.create()
                                                                           .setEndpointOverride(SmsProperties.ENDPOINT)
                                                //.setConnectTimeout(Duration.ofSeconds(30))
                                        )
                                        .build();


        // Parameter settings for API request
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                                                      .signName(SmsProperties.SIGN_NAME)
                                                      .templateCode(SmsProperties.TEMPLATE_CODE)
                                                      .phoneNumbers(phone)
                                                      .templateParam(jackson.bean2Json(smsVo))
                                                      // Request-level configuration rewrite, can set Http request parameters, etc.
                                                      // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                                                      .build();

        // Asynchronously get the return value of the API request
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        log.info("开始发送短信...");
        // Synchronously get the return value of the API request
        SendSmsResponse resp;
        try {
            resp = response.get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("发送短信失败: {}", e.getLocalizedMessage());
            e.printStackTrace();
            throw new GuliException(ErrorInfo.SEND_MSM_ERROR);
        }

        final String respJson = jackson.bean2Json(resp);
        final SendSmsResponseBody respBody = resp.getBody();

        if (StringUtils.isNotEmpty(respBody.getBizId())) {
            log.info("发送短信成功: {}", respJson);
        } else {
            log.error("发送短信失败: {}", respBody.getMessage());
            throw new GuliException(ErrorInfo.SEND_MSM_ERROR);
        }

        client.close();
    }

}
