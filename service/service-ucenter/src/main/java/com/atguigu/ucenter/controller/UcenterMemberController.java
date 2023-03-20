package com.atguigu.ucenter.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.service_pojo.pojo.UcenterMember;
import com.atguigu.ucenter.service.UcenterMemberService;
import com.atguigu.ucenter.vo.param.LoginParam;
import com.atguigu.ucenter.vo.param.RegisterParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author ginga
 * @since 15/1/2023 上午4:03
 */
@Api(tags = "单点登录")
@RequestMapping("edu-ucenter/member")
@RequiredArgsConstructor
@RestController
public class UcenterMemberController {

    private final UcenterMemberService memberService;

    @ApiOperation("用户登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginParam loginParam) {
        return memberService.login(loginParam);
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public Result register(@RequestBody RegisterParam registerParam) {
        return memberService.register(registerParam);
    }

    @ApiOperation("根据token获取用户信息")
    @GetMapping("current")
    public Result current(@RequestHeader("Authorization") String token) {
        return memberService.current(token);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("{id}")
    public Optional<UcenterMember> getMemberInfo(@PathVariable String id) {
        return memberService.getMemberInfo(id);
    }

    @ApiOperation("统计当日注册人数")
    @GetMapping("statistic/register/{date}")
    public Optional<Long> countRegister(@PathVariable String date) {
        return memberService.countRegister(date);
    }
}
