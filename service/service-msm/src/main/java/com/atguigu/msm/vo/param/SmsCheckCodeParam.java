package com.atguigu.msm.vo.param;

import lombok.Data;

/**
 * @author ginga
 * @since 15/1/2023 上午2:56
 */
@Data
public class SmsCheckCodeParam {
    private String phone;
    private String code;
}
