package com.atguigu.front.service;

import com.atguigu.common_utils.Result;
import com.atguigu.front.vo.param.CourseFrontParam;
import com.atguigu.service_pojo.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ginga
 * @since 17/1/2023 下午1:59
 */
public interface CourseFrontService extends IService<EduCourse> {
    /**
     * 查询课程列表
     *
     * @param courseFrontParam 课程列表查询参数
     * @return 课程列表
     */
    Result list(CourseFrontParam courseFrontParam);

    /**
     * 查询课程详情
     *
     * @param id 课程id
     * @return 课程详情
     */
    Result detail(String id);

    /**
     * 根据课程id和用户id查询课程是否购买
     *
     * @param courseId 课程id
     * @param token    用户token
     * @return 是否购买
     */
    Result status(String courseId, String token);
}
