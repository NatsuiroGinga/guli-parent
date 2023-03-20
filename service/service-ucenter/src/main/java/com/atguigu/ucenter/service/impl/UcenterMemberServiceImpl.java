package com.atguigu.ucenter.service.impl;

import com.atguigu.common_utils.ErrorInfo;
import com.atguigu.common_utils.Result;
import com.atguigu.common_utils.cache.CacheProperties;
import com.atguigu.common_utils.cache.CacheUtils;
import com.atguigu.common_utils.sso.JWTUtils;
import com.atguigu.common_utils.sso.MD5Utils;
import com.atguigu.service_base.aop.statistic.Statistic;
import com.atguigu.service_pojo.pojo.UcenterMember;
import com.atguigu.ucenter.client.MsmApiClient;
import com.atguigu.ucenter.converter.MemberConverter;
import com.atguigu.ucenter.mapper.UcenterMapper;
import com.atguigu.ucenter.service.UcenterMemberService;
import com.atguigu.ucenter.vo.LoginMemberVo;
import com.atguigu.ucenter.vo.param.LoginParam;
import com.atguigu.ucenter.vo.param.RegisterParam;
import com.atguigu.ucenter.vo.param.SmsCheckCodeParam;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author ginga
 * @since 15/1/2023 上午4:04
 */
@Service
@RequiredArgsConstructor
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMapper, UcenterMember>
        implements UcenterMemberService {

    private final MsmApiClient msmApiClient;

    private final MemberConverter memberConverter;

    private final StringRedisTemplate redisTemplate;

    @Statistic
    @Override
    public Result login(LoginParam loginParam) {
        Assert.notNull(loginParam, "登录参数不能为空");
        final String mobile = loginParam.getMobile();
        final String password = loginParam.getPassword();

        // 1 校验参数
        if (StringUtils.isAnyBlank(mobile, password)) {
            return Result.fail(ErrorInfo.PARAMS_ARE_BLANK);
        }
        final Optional<UcenterMember> ucenterMember = lambdaQuery().eq(UcenterMember::getMobile, mobile)
                                                                   .oneOpt();
        if (!ucenterMember.isPresent()) {
            return Result.fail(ErrorInfo.LOGIN_ERROR);
        }

        final UcenterMember member = ucenterMember.get();

        // 2 校验密码
        if (!StringUtils.equals(MD5Utils.encrypt(password), member.getPassword())) {
            return Result.fail(ErrorInfo.LOGIN_ERROR);
        }
        // 3 判断用户是否被禁用
        final Integer disabled = member.getDisabled();
        if (disabled == 1) {
            return Result.fail(ErrorInfo.LOGIN_ERROR);
        }

        // 4 生成token
        final String token = JWTUtils.createToken(member.getId(), member.getNickname());

        // 5 redis存储token
        final String redisKey = CacheUtils.createKey(CacheProperties.LOGIN_MEMBER.getKey(), member.getId());
        redisTemplate.opsForValue()
                     .set(redisKey, token, CacheProperties.LOGIN_MEMBER.getTimeout());

        return Result.success(token);
    }

    @Statistic
    @Override
    public Result register(RegisterParam registerParam) {
        Assert.notNull(registerParam, "注册参数不能为空");

        final String nickname = registerParam.getNickname();
        final String code = registerParam.getCode();
        final String mobile = registerParam.getMobile();
        final String password = registerParam.getPassword();

        //  1 校验参数
        if (StringUtils.isAnyBlank(nickname, code, mobile, password)) {
            return Result.fail(ErrorInfo.PARAMS_ARE_BLANK);
        }

        // 2 校验验证码
        final Result result = msmApiClient.checkCode(new SmsCheckCodeParam(mobile, code));
        if (!result.isSuccess()) {
            return Result.fail(ErrorInfo.REGISTER_ERROR);
        }

        // 3 判断手机号不重复
        Long count = this.lambdaQuery()
                         .eq(UcenterMember::getMobile, mobile)
                         .count();
        if (count > 0) {
            return Result.fail(ErrorInfo.MEMBER_ALREADY_EXISTS);
        }

        // 4 存数据库
        final UcenterMember ucenterMember = memberConverter.toMember(registerParam);
        ucenterMember.setPassword(MD5Utils.encrypt(password));
        ucenterMember.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");

        // 4.1 加锁, 防止并发注册多个相同手机号
        synchronized (mobile.intern()) {
            // 4.1 保存用户
            count = this.lambdaQuery()
                        .eq(UcenterMember::getMobile, mobile)
                        .count();
            if (count > 0) {
                return Result.fail(ErrorInfo.MEMBER_ALREADY_EXISTS);
            }

            return this.save(ucenterMember) ? Result.success() : Result.fail(ErrorInfo.REGISTER_ERROR);
        }
    }

    @Override
    public Result current(String token) {
        // 1 根据token获取用户信息
        final Optional<String> memberIdOpt = JWTUtils.getMemberIdByToken(token);
        if (!memberIdOpt.isPresent()) {
            return Result.fail(ErrorInfo.NOT_LOGIN);
        }
        final String memberId = memberIdOpt.get();

        // 2 根据用户id获取用户信息
        final UcenterMember member = this.getById(memberId);
        final LoginMemberVo memberVo = memberConverter.toMemberVo(member);

        return Result.success(memberVo);
    }

    @Override
    public Optional<UcenterMember> getMemberInfo(String id) {
        if (StringUtils.isBlank(id)) {
            return Optional.empty();
        }

        final UcenterMember member = this.getById(id);

        if (ObjectUtils.isEmpty(member)) {
            return Optional.empty();
        }

        return Optional.of(member);
    }

    @Override
    public Optional<Long> countRegister(String date) {

        // 1 校验参数
        if (StringUtils.isBlank(date) || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return Optional.empty();
        }

        // 2 查询当天注册人数
        final Long count = this.lambdaQuery()
                               .eq(UcenterMember::getGmtCreate, date)
                               .count();

        return Optional.of(count);
    }
}
