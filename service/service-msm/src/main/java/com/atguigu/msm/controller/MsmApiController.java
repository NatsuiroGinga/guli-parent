package com.atguigu.msm.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.msm.service.MsmService;
import com.atguigu.msm.vo.param.SmsCheckCodeParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author ginga
 * @since 14/1/2023 下午9:18
 */
@Api(tags = "短信管理")
@RequestMapping("api/msm")
@RestController
@RequiredArgsConstructor
public class MsmApiController {

    private final MsmService msmService;

    @ApiOperation("发送短信")
    @GetMapping("send/{phone}")
    public Result sendMsm(@PathVariable String phone) {
        return msmService.sendMsm(phone);
    }

    @ApiOperation("检查验证码")
    @PostMapping("check")
    public Result checkCode(@RequestBody SmsCheckCodeParam checkCodeParam) {
        return msmService.checkCode(checkCodeParam);
    }
}
