package com.atguigu.common_utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 缓存工具类
 *
 * @author ginga
 * @since 7/1/2023 上午12:21
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class CacheUtils {

    public static String separator = ":";

    @NotNull
    public static String createKey(@NotNull JoinPoint joinpoint) {
        Class<?> target = joinpoint.getTarget()
                                   .getClass();
        String methodName = joinpoint.getSignature().getName();
        Object[] args = joinpoint.getArgs();
        final StringBuilder paramsBuilder = new StringBuilder();
        // 1. 拼接类名和方法名
        String position = createKey(target, methodName);
        // 1.1 参数为空, 直接返回
        if (args == null || args.length == 0) {
            return StringUtils.substringBeforeLast(position, separator);
        }

        // 2. 拼接参数
        for (Object arg : args) {
            paramsBuilder.append(arg.toString());
        }

        // 3. 拼接参数和类名方法名, 参数进行MD5加密成32个字符, 防止过长
        return position + DigestUtils.md5Hex(paramsBuilder.toString());
    }

    /**
     * 拼接类名和方法名
     *
     * @param target     类
     * @param methodName 方法名
     * @return String 类名:方法名:
     */
    @NotNull
    @Contract(pure = true)
    public static String createKey(@NotNull Class<?> target, String methodName) {
        return target.getSimpleName() + separator + methodName + separator;
    }

}
