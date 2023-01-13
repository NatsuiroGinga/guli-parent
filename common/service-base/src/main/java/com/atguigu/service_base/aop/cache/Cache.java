package com.atguigu.service_base.aop.cache;

import com.atguigu.common_utils.CacheProperties;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

import static com.atguigu.common_utils.CacheProperties.DEFAULT;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Redis缓存注解
 * <p>用于返回Result类型的方法上
 *
 * @author ginga
 * @since 6/1/2023 下午11:40
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {

    /**
     * 缓存key的别名
     * <p>如果使用config()统一配置, 不要填写此项
     * <p>默认key为类名:方法名:参数md5Hex, 默认过期时间为60s
     */
    String value() default "";

    /**
     * 过期时间, 单位为s
     * <p>如果使用config()统一配置, 不要填写此项
     * <p>默认一分钟
     */
    long timeout() default 60;

    /**
     * 时间单位
     * <p>默认是秒
     */
    TimeUnit timeUnit() default SECONDS;

    /**
     * 缓存属性的枚举类
     * <p>配置key与timeout
     */
    CacheProperties config() default DEFAULT;

}
