package com.atguigu.common_utils;

/**
 * @author ginga
 * @version 1.0
 * @ClassName ResultCode
 * @Date 20/9/2022 下午3:29
 */
public enum ResultCode {

    SUCCESS(20000), // 成功
    FAIL(20001); // 失败

    private final int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
