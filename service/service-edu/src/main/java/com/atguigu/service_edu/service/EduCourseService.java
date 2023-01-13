package com.atguigu.service_edu.service;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.pojo.EduCourse;
import com.atguigu.service_edu.vo.param.CourseInfoParam;
import com.atguigu.service_edu.vo.param.CourseQueryParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 17400
 * @description 针对表【edu_course(课程基本信息)】的数据库操作Service
 * @createDate 2023-01-07 13:59:11
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程信息
     *
     * @param courseInfoParam 课程信息
     */
    Result add(CourseInfoParam courseInfoParam);

    /**
     * 根据课程id确认信息
     *
     * @param id 课程id
     */
    Result getPublishCourseInfo(String id);

    /**
     * 最终发布课程
     *
     * @param id 课程id
     */
    Result publish(String id);

    /**
     * 获取课程列表
     */
    Result listPages(Long current, Long limit, CourseQueryParam courseQueryParam);

    /**
     * 删除课程
     *
     * @param id 课程id
     */
    Result delete(String id);
}
