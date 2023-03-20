package com.atguigu.ucenter.vo.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ginga
 * @since 15/1/2023 上午2:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsCheckCodeParam {
    private String phone;
    private String code;
}
