package com.atguigu.service_edu.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.service_base.aop.cache.Cache;
import com.atguigu.service_edu.mapper.EduTeacherMapper;
import com.atguigu.service_edu.service.EduTeacherService;
import com.atguigu.service_edu.vo.param.TeacherQueryParam;
import com.atguigu.service_pojo.pojo.EduTeacher;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * @author 17400
 * @description 针对表【edu_teacher(讲师)】的数据库操作Service实现
 * @createDate 2022-09-15 17:08:42
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher>
        implements EduTeacherService {

    @Cache(timeout = 30, timeUnit = MINUTES)
    @Override
    public Result listPages(Long current, Long limit, @NotNull TeacherQueryParam teacherQueryParam) {

        final Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        final LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
        final String name = teacherQueryParam.getName();
        final Integer level = teacherQueryParam.getLevel();
        final String begin = teacherQueryParam.getBegin();
        final String end = teacherQueryParam.getEnd();

        queryWrapper.like(StringUtils.isNotBlank(name), EduTeacher::getName, name)
                    .eq(level != null, EduTeacher::getLevel, level)
                    .gt(StringUtils.isNotBlank(begin), EduTeacher::getGmtCreate, begin)
                    .lt(StringUtils.isNotBlank(end), EduTeacher::getGmtCreate, end);

        queryWrapper.orderByDesc(EduTeacher::getGmtCreate);

        final Page<EduTeacher> page = this.page(eduTeacherPage, queryWrapper);

        return Result.success(page);
    }
}




