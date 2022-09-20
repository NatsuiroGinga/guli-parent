package com.atguigu.common_utils;

import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 统一返回结果
 * @author ginga
 * @version 1.0
 * @ClassName Result
 * @Date 20/9/2022 下午3:32
 */
@Data
public class Result {

    private boolean success;

    private int code;

    private String msg;

    private Object data;

    private Result(boolean success, int code, String msg, Object data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @NotNull
    public static Result success(Object data) {
        return new Result(true, ResultCode.SUCCESS.getCode(), "success", data);
    }

    @NotNull
    @Contract(" -> new")
    public static Result fail() {
        return new Result(false, ResultCode.FAIL.getCode(), "fail", null);
    }

}
