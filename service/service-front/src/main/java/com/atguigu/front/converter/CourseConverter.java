package com.atguigu.front.converter;

import com.atguigu.service_pojo.pojo.EduCourse;
import com.atguigu.service_pojo.pojo.EduCourseDescription;
import com.atguigu.service_pojo.pojo.EduSubject;
import com.atguigu.service_pojo.pojo.EduTeacher;
import com.atguigu.service_pojo.vo.CourseVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * @author ginga
 * @since 17/1/2023 下午1:38
 */
@Mapper(componentModel = SPRING,
        unmappedSourcePolicy = IGNORE,
        unmappedTargetPolicy = IGNORE)
public interface CourseConverter {

    @Mapping(target = "teacherName", source = "teacher.name")
    @Mapping(target = "subjectLevelOne", source = "subjectLevelOne.title")
    @Mapping(target = "subjectLevelTwo", source = "subjectLevelTwo.title")
    @Mapping(target = "title", source = "eduCourse.title")
    @Mapping(target = "id", source = "eduCourse.id")
    CourseVo toCourseVo(EduCourse eduCourse, EduCourseDescription courseDescription, EduTeacher teacher,
                        EduSubject subjectLevelOne, EduSubject subjectLevelTwo);

    List<CourseVo> toCourseVoList(List<EduCourse> courseList);

}
