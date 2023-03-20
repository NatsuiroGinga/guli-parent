package com.atguigu.front.converter;

import com.atguigu.front.vo.IndexFrontVo;
import com.atguigu.service_pojo.pojo.EduCourse;
import com.atguigu.service_pojo.pojo.EduTeacher;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * @author ginga
 * @since 14/1/2023 下午5:01
 */
@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = IGNORE)
public interface IndexFrontConverter {

    IndexFrontVo toIndexFrontVo(List<EduTeacher> teacherList, List<EduCourse> courseList);
}
