package com.atguigu.service_base.aop.statistic;

import com.atguigu.common_utils.Result;
import com.atguigu.common_utils.cache.CacheUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author ginga
 * @since 20/1/2023 下午1:39
 */
@Component
@Slf4j
@RequiredArgsConstructor
@Aspect
public class StatisticAspect {

    private final StringRedisTemplate redisTemplate;

    @AfterReturning(value = "@annotation(statistic)", returning = "result")
    @Async
    public void statistic(@NotNull JoinPoint joinPoint, Statistic statistic, @NotNull Result result) {
        Assert.notNull(result, "result is null");
        // 1 判断返回结果是否成功
        if (!result.isSuccess()) {
            // 1.1 失败则不统计
            return;
        }
        // 2 类名 + 方法名 构成 redis key
        final Class<?> target = joinPoint.getTarget()
                                         .getClass();
        final Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        final String redisKey = CacheUtils.createKeyWithoutSuffix(target, methodName);
        // 3 统计, 用 redis 的 incr 命令
        log.info("{} 执行, 正在统计...", signature);
        redisTemplate.opsForValue()
                     .increment(redisKey);
    }

}
