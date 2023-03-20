package com.atguigu.front.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.front.converter.IndexFrontConverter;
import com.atguigu.front.mapper.EduCourseMapper;
import com.atguigu.front.mapper.EduTeacherMapper;
import com.atguigu.front.service.IndexFrontService;
import com.atguigu.front.vo.IndexFrontVo;
import com.atguigu.service_base.aop.cache.Cache;
import com.atguigu.service_pojo.pojo.EduCourse;
import com.atguigu.service_pojo.pojo.EduTeacher;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ginga
 * @since 14/1/2023 下午4:52
 */
@Service
@RequiredArgsConstructor
public class IndexFrontServiceImpl implements IndexFrontService {

    private final EduTeacherMapper teacherMapper;

    private final EduCourseMapper courseMapper;

    private final IndexFrontConverter indexFrontConverter;

    @Cache(timeout = 1, timeUnit = TimeUnit.DAYS)
    @Override
    public Result index() {
        final LambdaQueryChainWrapper<EduCourse> courseQueryWrapper = new LambdaQueryChainWrapper<>(courseMapper);
        final LambdaQueryChainWrapper<EduTeacher> teacherQueryWrapper = new LambdaQueryChainWrapper<>(teacherMapper);
        // 1 查询前8条热门课程
        final List<EduCourse> courseList = courseQueryWrapper.orderByDesc(EduCourse::getViewCount)
                                                             .last("limit 8")
                                                             .list();
        // 2 查询前4条名师
        final List<EduTeacher> teacherList = teacherQueryWrapper.orderByDesc(EduTeacher::getId)
                                                                .last("limit 4")
                                                                .list();

        // 3 封装数据
        final IndexFrontVo indexFrontVo = indexFrontConverter.toIndexFrontVo(teacherList, courseList);

        return Result.success(indexFrontVo);
    }
}
