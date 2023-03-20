package com.atguigu.front.service;

import com.atguigu.common_utils.Result;
import com.atguigu.front.vo.param.TeacherPageParam;
import com.atguigu.service_pojo.pojo.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ginga
 * @since 17/1/2023 上午12:09
 */
public interface TeacherFrontService extends IService<EduTeacher> {
    /**
     * 分页查询讲师
     *
     * @param pageParam 分页参数
     * @return 分页结果
     */
    Result listPages(TeacherPageParam pageParam);

    /**
     * 查询讲师详情
     *
     * @param id 讲师id
     * @return 讲师详情
     */
    Result profile(String id);
}
