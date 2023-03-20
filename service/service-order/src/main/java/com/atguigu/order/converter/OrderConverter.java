package com.atguigu.order.converter;

import com.atguigu.service_pojo.pojo.Order;
import com.atguigu.service_pojo.pojo.UcenterMember;
import com.atguigu.service_pojo.vo.CourseVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author ginga
 * @since 17/1/2023 下午11:05
 */
@Mapper(componentModel = SPRING)
public interface OrderConverter {

    @Mapping(target = "version", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "payType", ignore = true)
    @Mapping(target = "totalFee", source = "courseVo.price")
    @Mapping(target = "orderNo", ignore = true)
    @Mapping(target = "memberId", source = "member.id")
    @Mapping(target = "courseTitle", source = "courseVo.title")
    @Mapping(target = "courseId", source = "courseVo.id")
    @Mapping(target = "courseCover", source = "courseVo.cover")
    @Mapping(target = "id", ignore = true)
    Order toOrder(CourseVo courseVo, UcenterMember member);

}
