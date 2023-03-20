package com.atguigu.service_edu.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.service_base.aop.cache.Cache;
import com.atguigu.service_base.aop.cache.ClearCache;
import com.atguigu.service_base.aop.statistic.Statistic;
import com.atguigu.service_base.exceptionhandler.exception.GuliException;
import com.atguigu.service_base.util.CourseStatus;
import com.atguigu.service_edu.converter.EduCourseConverter;
import com.atguigu.service_edu.converter.EduCourseDescriptionConverter;
import com.atguigu.service_edu.mapper.EduChapterMapper;
import com.atguigu.service_edu.mapper.EduCourseDescriptionMapper;
import com.atguigu.service_edu.mapper.EduCourseMapper;
import com.atguigu.service_edu.mapper.EduVideoMapper;
import com.atguigu.service_edu.mapper.manager.EduVideoManager;
import com.atguigu.service_edu.service.EduCourseService;
import com.atguigu.service_edu.vo.CoursePublishVO;
import com.atguigu.service_edu.vo.param.CourseInfoParam;
import com.atguigu.service_edu.vo.param.CourseQueryParam;
import com.atguigu.service_pojo.pojo.EduCourse;
import com.atguigu.service_pojo.pojo.EduCourseDescription;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.atguigu.common_utils.ErrorInfo.*;
import static java.util.concurrent.TimeUnit.HOURS;

/**
 * @author 17400
 * @description 针对表【edu_course(课程基本信息)】的数据库操作Service实现
 * @createDate 2023-01-07 13:59:11
 */
@Service
@RequiredArgsConstructor
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
        implements EduCourseService {

    private final EduCourseConverter courseConverter;

    private final EduCourseDescriptionConverter courseDescriptionConverter;

    private final EduCourseDescriptionMapper courseDescriptionMapper;

    private final EduVideoMapper videoMapper;

    private final EduChapterMapper chapterMapper;

    private final EduVideoManager videoManager;

    @Statistic
    @Transactional(rollbackFor = GuliException.class)
    @Override
    public Result add(@NotNull CourseInfoParam courseInfoParam) {
        final String title = courseInfoParam.getTitle();
        final String description = courseInfoParam.getDescription();

        if (StringUtils.isAnyBlank(title, description)) {
            return Result.fail(PARAMS_ARE_BLANK);
        }

        // 1. 保存课程信息
        final EduCourse course = courseConverter.toEduCourse(courseInfoParam);
        if (!this.save(course)) {
            return Result.fail(ADD_COURSE_ERROR);
        }
        // 2. 保存课程简介
        final EduCourseDescription courseDescription = courseDescriptionConverter.toEdCourseDescription(courseInfoParam);
        final String courseId = course.getId();
        courseDescription.setId(courseId);
        if (courseDescriptionMapper.insert(courseDescription) == 0) {
            throw new GuliException(ADD_COURSE_ERROR.getCode(), ADD_COURSE_ERROR.getMsg());
        }

        return Result.success(courseId);
    }

    @Override
    public Result getPublishCourseInfo(String id) {
        final CoursePublishVO publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return Result.success(publishCourseInfo);
    }

    @ClearCache("listPages")
    @Override
    public Result publish(String id) {
        final EduCourse normalCourse = EduCourse.builder()
                                                .id(id)
                                                .status(CourseStatus.NORMAL.getStatus())
                                                .build();
        return this.updateById(normalCourse) ? Result.success() : Result.fail(PUBLISH_COURSE_ERROR);
    }

    @Cache(timeout = 1, timeUnit = HOURS)
    @Override
    public Result listPages(Long current, Long limit, CourseQueryParam courseQueryParam) {

        final Page<EduCourse> coursePage = new Page<>(current, limit);
        final LambdaQueryChainWrapper<EduCourse> chainWrapper = new LambdaQueryChainWrapper<>(baseMapper);
        Assert.notNull(courseQueryParam, "courseQueryParam is null");

        final String title = courseQueryParam.getTitle();
        final String status = courseQueryParam.getStatus();

        final Page<EduCourse> page = chainWrapper.like(StringUtils.isNotBlank(title), EduCourse::getTitle, title)
                                                 .eq(StringUtils.isNotBlank(status), EduCourse::getStatus, status)
                                                 .select(EduCourse::getId, EduCourse::getTitle, EduCourse::getLessonNum,
                                                         EduCourse::getStatus, EduCourse::getGmtCreate)
                                                 .page(coursePage);

        return Result.success(page);
    }

    @ClearCache("listPages")
    @Transactional(rollbackFor = GuliException.class)
    @Override
    public Result delete(String id) {
        // 1 根据课程id删除阿里云视频和小节
        videoManager.deleteVideoByCourseId(id);
        // 2 根据课程id删除章节
        if (!chapterMapper.deleteByCourseId(id)) {
            throw new GuliException(DELETE_CHAPTER_ERROR.getCode(), DELETE_CHAPTER_ERROR.getMsg());
        }
        // 3 根据课程id删除课程和课程描述
        if (courseDescriptionMapper.deleteById(id) == 0 || !this.removeById(id)) {
            throw new GuliException(DELETE_CHAPTER_ERROR.getCode(), DELETE_CHAPTER_ERROR.getMsg());
        }

        return Result.success();
    }

    @Override
    public Result detail(String id) {
        final EduCourse course = this.getById(id);
        return Result.success(course);
    }
}

