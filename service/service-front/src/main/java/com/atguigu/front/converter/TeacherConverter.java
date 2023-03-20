package com.atguigu.front.converter;

import com.atguigu.front.vo.TeacherProfileVo;
import com.atguigu.front.vo.TeacherVo;
import com.atguigu.service_pojo.pojo.EduCourse;
import com.atguigu.service_pojo.pojo.EduTeacher;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * @author ginga
 * @since 17/1/2023 下午1:37
 */
@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = IGNORE,
        unmappedSourcePolicy = IGNORE,
        uses = CourseConverter.class)
public interface TeacherConverter {

    TeacherVo toTeacherVo(EduTeacher teacher);

    TeacherProfileVo toTeacherProfileVo(EduTeacher teacher, List<EduCourse> courseList);

}
