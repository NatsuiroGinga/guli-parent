package com.atguigu.service_edu.service;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.pojo.EduTeacher;
import com.atguigu.service_edu.vo.param.PageParam;
import com.atguigu.service_edu.vo.param.TeacherQueryParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 17400
* @description 针对表【edu_teacher(讲师)】的数据库操作Service
* @createDate 2022-09-15 17:08:42
*/
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 分页查询教师
     * @param pageParam  分页参数
     * @return com.atguigu.common_utils.Result
     * @author ginga
     * @Date 20/9/2022 下午5:07
     **/
    Result pageListTeacher(Long current, Long limit, PageParam pageParam);
}
