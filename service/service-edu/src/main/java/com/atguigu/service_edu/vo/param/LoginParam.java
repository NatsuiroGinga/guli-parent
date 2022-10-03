package com.atguigu.service_edu.vo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录参数
 * @author ginga
 * @version 1.0
 * @ClassName LoginParam
 * @Date 2/10/2022 上午11:09
 */
@Data
public class LoginParam {

    @ApiModelProperty("账户")
    private String account;

    @ApiModelProperty("密码")
    private String password;

}
