package com.atguigu.service_edu.converter;

import com.atguigu.service_edu.vo.param.CourseInfoParam;
import com.atguigu.service_pojo.pojo.EduCourse;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * @author ginga
 * @since 7/1/2023 下午2:31
 */
@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = IGNORE,
        injectionStrategy = CONSTRUCTOR)
public interface EduCourseConverter {

    EduCourse toEduCourse(CourseInfoParam courseInfoParam);

}
