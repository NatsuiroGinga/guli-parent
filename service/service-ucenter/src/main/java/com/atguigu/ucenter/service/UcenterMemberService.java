package com.atguigu.ucenter.service;

import com.atguigu.common_utils.Result;
import com.atguigu.service_pojo.pojo.UcenterMember;
import com.atguigu.ucenter.vo.param.LoginParam;
import com.atguigu.ucenter.vo.param.RegisterParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Optional;

/**
 * 用户登录注册业务接口
 *
 * @author ginga
 * @since 15/1/2023 上午4:04
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    /**
     * 登录
     *
     * @param loginParam 登录参数
     * @return token
     */
    Result login(LoginParam loginParam);

    /**
     * 注册
     *
     * @param registerParam 注册参数
     */
    Result register(RegisterParam registerParam);

    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return 用户信息
     */
    Result current(String token);

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    Optional<UcenterMember> getMemberInfo(String id);

    /**
     * 统计当日注册人数
     *
     * @param date 日期 yyyy-MM-dd
     * @return 当日注册人数
     */
    Optional<Long> countRegister(String date);
}
