package com.atguigu.service_edu.converter;

import com.atguigu.service_edu.pojo.EduCourseDescription;
import com.atguigu.service_edu.vo.param.CourseInfoParam;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * @author ginga
 * @since 7/1/2023 下午2:39
 */
@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = IGNORE,
        injectionStrategy = CONSTRUCTOR)
public interface EduCourseDescriptionConverter {

    @Mapping(target = "id", ignore = true)
    EduCourseDescription toEdCourseDescription(CourseInfoParam courseInfoParam);

}