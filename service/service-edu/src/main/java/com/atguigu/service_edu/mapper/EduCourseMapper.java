package com.atguigu.service_edu.mapper;

import com.atguigu.service_edu.vo.CoursePublishVO;
import com.atguigu.service_pojo.pojo.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 17400
 * @description 针对表【edu_course(课程基本信息)】的数据库操作Mapper
 * @createDate 2023-01-07 13:59:11
 * @Entity com.atguigu.service_edu.pojo.EduCourse
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVO getPublishCourseInfo(@Param("courseId") String courseId);

}




