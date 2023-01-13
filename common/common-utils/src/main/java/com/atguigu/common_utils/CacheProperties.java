package com.atguigu.common_utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

/**
 * @author ginga
 * @since 7/1/2023 上午12:10
 */
@RequiredArgsConstructor
@Getter
public enum CacheProperties {

    /**
     * 默认缓存参数
     * <p>key: 类名:方法名:方法入参拼接成字符串后md5编码</p>
     * <p>timeout: 60s</p>
     */
    DEFAULT("", Duration.ofSeconds(60));

    private final String key;

    private final Duration timeout;

}
