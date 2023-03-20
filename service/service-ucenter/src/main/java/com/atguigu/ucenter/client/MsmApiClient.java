package com.atguigu.ucenter.client;

import com.atguigu.common_utils.Result;
import com.atguigu.ucenter.vo.param.SmsCheckCodeParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ginga
 * @since 16/1/2023 上午1:07
 */
@FeignClient(value = "service-msm", path = "/api/msm")
public interface MsmApiClient {

    @PostMapping("check")
    Result checkCode(@RequestBody SmsCheckCodeParam checkCodeParam);
}
