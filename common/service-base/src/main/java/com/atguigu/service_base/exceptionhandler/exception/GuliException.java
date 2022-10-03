package com.atguigu.service_base.exceptionhandler.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ginga
 * @version 1.0
 * @ClassName GuliException
 * @Date 22/9/2022 下午9:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{

    // 状态码
    private int code;

    // 异常信息
    private String msg;
}
