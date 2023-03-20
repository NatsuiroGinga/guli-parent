package com.atguigu.ucenter.converter;

import com.atguigu.service_pojo.pojo.UcenterMember;
import com.atguigu.ucenter.vo.LoginMemberVo;
import com.atguigu.ucenter.vo.param.RegisterParam;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author ginga
 * @since 16/1/2023 上午1:18
 */
@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MemberConverter {

    UcenterMember toMember(RegisterParam registerParam);

    LoginMemberVo toMemberVo(UcenterMember member);

}
