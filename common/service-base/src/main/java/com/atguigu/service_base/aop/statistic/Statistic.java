package com.atguigu.service_base.aop.statistic;

import java.lang.annotation.*;

/**
 * 统计注解 <br>
 * 所注解的方法返回值必须是 Result 类型
 *
 * @author ginga
 * @since 20/1/2023 下午1:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Statistic {
}
