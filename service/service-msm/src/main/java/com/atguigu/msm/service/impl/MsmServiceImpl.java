package com.atguigu.msm.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.atguigu.common_utils.ErrorInfo;
import com.atguigu.common_utils.Result;
import com.atguigu.common_utils.cache.CacheUtils;
import com.atguigu.msm.manager.MsmManager;
import com.atguigu.msm.service.MsmService;
import com.atguigu.msm.vo.SMSVo;
import com.atguigu.msm.vo.param.SmsCheckCodeParam;
import com.atguigu.service_base.exceptionhandler.exception.GuliException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.atguigu.common_utils.cache.CacheProperties.SMS_CODE;

/**
 * @author ginga
 * @since 15/1/2023 上午1:06
 */
@Service
@RequiredArgsConstructor
public class MsmServiceImpl implements MsmService {

    private final StringRedisTemplate redisTemplate;

    private final MsmManager msmManager;

    @Override
    public Result sendMsm(String phone) {

        if (StringUtils.isBlank(phone)) {
            return Result.fail(ErrorInfo.PARAMS_ARE_BLANK);
        }

        // 1 生成4位随机数
        final String code = RandomUtil.randomNumbers(4);
        // 2 生成redis key
        final String redisKey = CacheUtils.createKey(SMS_CODE.getKey(), phone);
        final SMSVo smsVo = new SMSVo(code);

        // 3 发送短信
        try {
            // 对手机号加锁，防止并发
            synchronized (phone.intern()) {
                // 3.1 异步发送
                msmManager.sendSMS(phone, smsVo);
                // 3.2 redis缓存
                redisTemplate.opsForValue()
                             .set(redisKey, code, SMS_CODE.getTimeout());
            }

        } catch (GuliException e) {
            return Result.fail(ErrorInfo.SEND_MSM_ERROR);
        }

        return Result.success();
    }

    @Override
    public Result checkCode(SmsCheckCodeParam checkCodeParam) {
        Assert.notNull(checkCodeParam, "checkCodeParam is null");
        final String code = checkCodeParam.getCode();
        final String phone = checkCodeParam.getPhone();

        if (StringUtils.isAnyBlank(code, phone)) {
            return Result.fail(ErrorInfo.PARAMS_ARE_BLANK);
        }

        // 1 从redis中取出验证码
        final String redisKey = CacheUtils.createKey(SMS_CODE.getKey(), phone);
        final String redisCode = redisTemplate.opsForValue()
                                              .get(redisKey);

        // 2 比较
        if (!StringUtils.equals(code, redisCode)) {
            return Result.fail(ErrorInfo.CODE_IS_ERROR);
        }

        return Result.success();
    }
}
