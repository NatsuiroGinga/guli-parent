package com.atguigu.service_base.exceptionhandler.exception;

import com.atguigu.common_utils.ErrorInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @author ginga
 * @version 1.0
 * @ClassName GuliException
 * @Date 22/9/2022 下午9:29
 */
@RequiredArgsConstructor
@Getter
public class GuliException extends RuntimeException {

    // 状态码
    private final int code;

    // 异常信息
    private final String msg;

    public GuliException(@NotNull ErrorInfo errorInfo) {
        this(errorInfo.getCode(), errorInfo.getMsg());
    }
}
