package com.atguigu.front.service.impl;

import com.atguigu.common_utils.ErrorInfo;
import com.atguigu.common_utils.Result;
import com.atguigu.front.converter.TeacherConverter;
import com.atguigu.front.mapper.EduCourseMapper;
import com.atguigu.front.mapper.EduTeacherMapper;
import com.atguigu.front.service.TeacherFrontService;
import com.atguigu.front.vo.TeacherPageVo;
import com.atguigu.front.vo.TeacherProfileVo;
import com.atguigu.front.vo.param.TeacherPageParam;
import com.atguigu.service_pojo.pojo.EduCourse;
import com.atguigu.service_pojo.pojo.EduTeacher;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author ginga
 * @since 17/1/2023 上午12:10
 */
@Service
@RequiredArgsConstructor
public class TeacherFrontServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher>
        implements TeacherFrontService {

    private final EduCourseMapper courseMapper;

    private final TeacherConverter teacherConverter;

    @SneakyThrows
    @Override
    public Result listPages(TeacherPageParam pageParam) {
        Assert.notNull(pageParam, "分页参数不能为空");
        final long current = pageParam.getCurrent();
        final long size = pageParam.getSize();
        Page<EduTeacher> teacherPage = new Page<>(current, size);
        final TeacherPageVo teacherPageVo = new TeacherPageVo();

        // 1 分页
        this.lambdaQuery()
            .orderByDesc(EduTeacher::getId)
            .page(teacherPage);

        // 2 转换
        BeanUtils.copyProperties(teacherPageVo, teacherPage);
        teacherPageVo.setHasNext(teacherPage.hasNext());
        teacherPageVo.setHasPrevious(teacherPage.hasPrevious());

        return Result.success(teacherPageVo);
    }

    @Override
    public Result profile(String id) {

        if (StringUtils.isBlank(id)) {
            return Result.fail(ErrorInfo.PARAMS_ARE_BLANK);
        }
        final LambdaQueryChainWrapper<EduCourse> courseQueryWrapper = new LambdaQueryChainWrapper<>(courseMapper);

        // 1 查询讲师信息
        final EduTeacher teacher = this.getById(id);
        // 2 查询讲师教授的所有课程
        final List<EduCourse> courseList = courseQueryWrapper.eq(EduCourse::getTeacherId, id)
                                                             .list();
        // 3 转化
        final TeacherProfileVo teacherProfileVo = teacherConverter.toTeacherProfileVo(teacher, courseList);

        return Result.success(teacherProfileVo);
    }
}
