package com.atguigu.msm.service;

import com.atguigu.common_utils.Result;
import com.atguigu.msm.vo.param.SmsCheckCodeParam;

/**
 * @author ginga
 * @since 15/1/2023 上午1:06
 */
public interface MsmService {
    /**
     * 发送短信
     *
     * @param phone 手机号
     * @return 验证码
     */
    Result sendMsm(String phone);

    /**
     * 检查验证码
     *
     * @param checkCodeParam 验证码参数
     * @return 是否正确
     */
    Result checkCode(SmsCheckCodeParam checkCodeParam);
}
