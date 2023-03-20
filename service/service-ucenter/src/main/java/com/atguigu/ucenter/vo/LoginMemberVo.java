package com.atguigu.ucenter.vo;

import lombok.Data;

/**
 * @author ginga
 * @since 16/1/2023 下午4:12
 */
@Data
public class LoginMemberVo {
    private String id;
    private String openid;
    private String mobile;
    private String nickname;
    private Integer sex;
    private Integer age;
    private String avatar;
    private String sign;
}
