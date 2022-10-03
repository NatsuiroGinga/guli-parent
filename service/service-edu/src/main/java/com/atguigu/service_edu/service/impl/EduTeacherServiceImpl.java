package com.atguigu.service_edu.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.vo.param.PageParam;
import com.atguigu.service_edu.vo.param.TeacherQueryParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.service_edu.pojo.EduTeacher;
import com.atguigu.service_edu.service.EduTeacherService;
import com.atguigu.service_edu.mapper.EduTeacherMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.naming.factory.BeanFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

/**
* @author 17400
* @description 针对表【edu_teacher(讲师)】的数据库操作Service实现
* @createDate 2022-09-15 17:08:42
*/
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher>
    implements EduTeacherService{

    @Override
    public Result pageListTeacher(Long current, Long limit, @NotNull PageParam pageParam) {

        final Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        final LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
        final String name = pageParam.getName();
        final Integer level = pageParam.getLevel();
        final String begin = pageParam.getBegin();
        final String end = pageParam.getEnd();

        queryWrapper.like(StringUtils.isNotBlank(name), EduTeacher::getName, name)
                .eq(level != null, EduTeacher::getLevel, level)
                .gt(StringUtils.isNotBlank(begin), EduTeacher::getGmtCreate, begin)
                .lt(StringUtils.isNotBlank(end), EduTeacher::getGmtCreate, end);

        final Page<EduTeacher> page = this.page(eduTeacherPage, queryWrapper);

        return Result.success(page);
    }
}




