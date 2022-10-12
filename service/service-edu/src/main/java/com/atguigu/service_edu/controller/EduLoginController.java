package com.atguigu.service_edu.controller;

import com.atguigu.common_utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author ginga
 * @version 1.0
 * @ClassName EduLoginController
 * @Date 2/10/2022 上午11:06
 */
@Api(tags = "用户登录")
@RestController
@RequestMapping("edu-service/user")
public class EduLoginController {

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result login() {
        final HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("token", "admin");
        return Result.success(hashMap);
    }

    @ApiOperation("用户信息")
    @GetMapping("info")
    public Result info() {

        final HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("roles", "[admin]");
        hashMap.put("name", "admin");
        hashMap.put("avatar",
                "http://img.ottofans.top/bf5517ea-d147-4500-afa6-556af4ac4d9e.jpg");

        return Result.success(hashMap);
    }

}
