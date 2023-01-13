package com.atguigu.common_utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author ginga
 * @version 1.0
 * @ClassName ResultCode
 * @Date 20/9/2022 下午3:29
 */
@RequiredArgsConstructor
@Getter
public enum ResultCode {

    SUCCESS(20000), // 成功码
    FAIL(20001); // 通用失败码

    private final int code;

}
