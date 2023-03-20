package com.atguigu.ucenter.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ginga
 * @since 16/1/2023 上午12:57
 */
@ApiModel(description = "注册参数")
@Data
public class RegisterParam {

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String code;
}
