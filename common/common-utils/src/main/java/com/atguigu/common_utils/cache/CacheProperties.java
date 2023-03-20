package com.atguigu.common_utils.cache;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

/**
 * 缓存的key与过期时间枚举
 *
 * @author ginga
 * @since 7/1/2023 上午12:10
 */
@RequiredArgsConstructor
@Getter
public enum CacheProperties {

    /**
     * 默认缓存参数
     * <p>key: 使用com.atguigu.common_utils.cache.CacheUtils.createKey生成</p>
     * <p>timeout: 60s</p>
     */
    DEFAULT("", Duration.ofSeconds(60)),
    /**
     * 短信验证码缓存参数
     * <p>key: code:sms</p>
     * <p>timeout: 5min</p>
     */
    SMS_CODE("code:sms", Duration.ofMinutes(5)),

    /**
     * 登录用户token缓存参数
     * <p>key: login:member</p>
     * <p>timeout: 30min</p>
     */
    LOGIN_MEMBER("login:member", Duration.ofMinutes(30)),
    ;

    /**
     * 缓存key, 可以添加自定义后缀
     */
    private final String key;

    /**
     * 缓存过期时间
     */
    private final Duration timeout;

}
