package com.atguigu.service_base.aop.cache;

import com.atguigu.common_utils.ClearCacheTime;

import java.lang.annotation.*;

import static com.atguigu.common_utils.ClearCacheTime.BEFORE;

/**
 * 清空缓存
 *
 * @author ginga
 * @since 7/1/2023 上午11:46
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ClearCache {

    /**
     * methodName的别名
     */
    String value();

    /**
     * methodName 所属的类, 不能写接口, 必须是具体的实现类
     * <p>默认是此注解所在的类
     */
    Class<?> target() default Object.class;

    /**
     * 方法名
     * <p>必须是标记了@Cache的方法
     */
    String methodName() default "";

    /**
     * 删除缓存的时刻
     * <p>默认是先删除再执行方法
     * <p>如果是先执行方法再删除缓存, 请设置为 AFTER
     */
    ClearCacheTime when() default BEFORE;

}
