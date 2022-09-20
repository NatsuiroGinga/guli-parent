package com.atguigu.service_base.config;

import com.atguigu.common_utils.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * @author ginga
 * @version 1.0
 * @ClassName GlobalExceptionHandler
 * @Date 20/9/2022 下午10:01
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(@NotNull Exception e) {
        e.printStackTrace();
        return Result.fail("当前系统忙...请稍后再访问");
    }
}
