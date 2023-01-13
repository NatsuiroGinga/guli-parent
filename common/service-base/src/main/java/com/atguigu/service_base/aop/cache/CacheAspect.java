package com.atguigu.service_base.aop.cache;

import com.atguigu.common_utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.atguigu.common_utils.CacheProperties.DEFAULT;
import static com.atguigu.common_utils.ErrorInfo.CACHE_ERROR;
import static com.atguigu.common_utils.ErrorInfo.CLEAR_CACHE_ERROR;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Redis缓存切面
 *
 * @author ginga
 * @since 7/1/2023 上午12:30
 */
@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class CacheAspect {

    private final StringRedisTemplate redisTemplate;

    private final Jackson jackson;

    /**
     * 统一缓存处理
     * <p>用于返回Result类型的方法上
     *
     * @param joinPoint 切点
     * @param cache     缓存注解
     * @return Object 实际上是Result
     */
    @Around("@annotation(cache)")
    public Object cache(ProceedingJoinPoint joinPoint, @NotNull Cache cache) {
        final String value = cache.value();
        final TimeUnit timeUnit = cache.timeUnit();
        final CacheProperties config = cache.config();
        final String key = config.getKey();
        Duration timeout;
        final long longTimeout = cache.timeout();
        final Duration configTimeout = config.getTimeout();

        // 根据时间单位转换时间
        if (timeUnit != SECONDS) {
            // 如果config()为默认值, 则使用timeout()的值, 否则使用config的timeout
            timeout = config == DEFAULT ? Duration.ofSeconds(timeUnit.toSeconds(longTimeout)) : configTimeout;
        } else {
            timeout = config == DEFAULT ? Duration.ofSeconds(longTimeout) : configTimeout;
        }

        // 1. 获取缓存key
        final String cacheKey = getCacheKey(key, value, joinPoint);
        // 2. 获取缓存value
        String cacheValue = getCacheValue(cacheKey, joinPoint, timeout);
        // 2.1 缓存命中, 反序列化后返回结果
        if (cacheValue != null) {
            return jackson.json2Bean(cacheValue, Result.class);
        }
        // 2.2 缓存未命中, 执行方法, 并将结果序列化后存入缓存
        return getResult(cacheKey, timeout, joinPoint);
    }

    /**
     * 清除缓存处理
     *
     * @param joinPoint  切点
     * @param clearCache 清除缓存注解
     * @return Object
     */
    @Around("@annotation(clearCache)")
    public Object clearCache(ProceedingJoinPoint joinPoint, @NotNull ClearCache clearCache) {
        final String value = clearCache.value();
        final ClearCacheTime when = clearCache.when();
        // 1. 获取方法名
        final String methodName = StringUtils.isBlank(value) ? clearCache.methodName() : value;
        // 2. 获取目标类, 如果为默认的Object, 则使用注解所在的类
        final Class<?> target = clearCache.target() == Object.class ? joinPoint.getTarget()
                                                                               .getClass() : clearCache.target();
        // 3. 创建key通配字符串
        final String cacheKeyPattern = CacheUtils.createKey(target, methodName) + "*";
        Long delete;
        Object result;

        // 4. 根据不同策略删除缓存与执行方法
        if (Objects.requireNonNull(when) == ClearCacheTime.BEFORE) {
            delete = delete(cacheKeyPattern);
            log.info("{} 清除缓存: {} 条", joinPoint.getSignature(), delete);
            try {
                result = joinPoint.proceed();
            } catch (Throwable e) {
                return Result.fail(CLEAR_CACHE_ERROR);
            }
        } else {
            try {
                result = joinPoint.proceed();
            } catch (Throwable e) {
                return Result.fail(CLEAR_CACHE_ERROR);
            }
            delete = delete(cacheKeyPattern);
            log.info("{} 清除缓存: {} 条", joinPoint.getSignature(), delete);
        }

        return result;
    }

    /**
     * 根据通配字符串批量删除
     *
     * @param keyPattern key通配
     * @return 删除的数量
     */
    private Long delete(String keyPattern) {
        final Set<String> keys = redisTemplate.keys(keyPattern);
        Long count = null;
        if (keys != null) {
            count = redisTemplate.delete(keys);
        }
        return count;
    }

    /**
     * 获取缓存的key
     */
    private String getCacheKey(String key, String value, ProceedingJoinPoint joinPoint) {
        // 1. 获取缓存的key
        String cacheKey;
        // 1.1 如果用户没有配置key, 使用CacheUtils生成
        if (StringUtils.isBlank(key) && StringUtils.isBlank(value)) {
            cacheKey = CacheUtils.createKey(joinPoint);
        } else { // 1.2 如果用户配置了key, 使用用户配置的key
            cacheKey = StringUtils.isBlank(key) ? value : key;
        }

        return cacheKey;
    }

    /**
     * 获取缓存的value
     */
    @Nullable
    private String getCacheValue(String cacheKey, ProceedingJoinPoint joinPoint, Duration timeout) {
        // 2. 获取缓存的值
        String cacheValue;
        // 2.1 如果缓存中有值, 刷新缓存过期时间并直接返回
        cacheValue = redisTemplate.opsForValue()
                                  .get(cacheKey);
        if (StringUtils.isNotBlank(cacheValue)) {
            log.info("{} 读取了缓存", joinPoint.getSignature());
            // 2.2 刷新缓存过期时间
            redisTemplate.expire(cacheKey, timeout.getSeconds(), SECONDS);
            return cacheValue;
        }

        return null;
    }

    /**
     * 执行方法, 获取结果, 并将结果存入缓存
     */
    @NotNull
    private Result getResult(String cacheKey, Duration timeout, ProceedingJoinPoint joinPoint) {
        String cacheValue;
        Result result;
        // 1 缓存中没有, 执行方法获取结果
        try {
            result = (Result) joinPoint.proceed();
        } catch (Throwable e) {
            return Result.fail(CACHE_ERROR);
        }

        // 2. 如果结果失败了, 不做缓存直接返回
        if (!result.isSuccess()) {
            return result;
        }

        cacheValue = jackson.bean2Json(result);
        // 3. 如果结果序列化失败, 返回错误信息
        if (cacheValue == null) {
            return Result.fail(CACHE_ERROR);
        }
        // 4. 存入缓存, 设置过期时间
        log.info("{} 存入了缓存", joinPoint.getSignature());
        redisTemplate.opsForValue()
                     .set(cacheKey, cacheValue, timeout);

        return result;
    }

}
