package com.atguigu.service_base.exceptionhandler;

import com.atguigu.common_utils.Result;
import com.atguigu.service_base.exceptionhandler.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(@NotNull Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.fail("当前系统忙...请稍后再访问");
    }

    // 特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(@NotNull ArithmeticException e) {
        e.printStackTrace();
        return Result.fail("执行了ArithmeticException异常处理");
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(@NotNull GuliException e) {
        e.printStackTrace();
        return Result.fail(e.getCode(), e.getMsg());
    }


}
