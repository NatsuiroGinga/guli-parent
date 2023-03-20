package com.atguigu.front.service.impl;

import com.atguigu.common_utils.ErrorInfo;
import com.atguigu.common_utils.Result;
import com.atguigu.common_utils.sso.JWTUtils;
import com.atguigu.front.client.EduChapterClient;
import com.atguigu.front.client.OrderClient;
import com.atguigu.front.converter.CourseConverter;
import com.atguigu.front.mapper.EduCourseDescriptionMapper;
import com.atguigu.front.mapper.EduCourseMapper;
import com.atguigu.front.mapper.EduSubjectMapper;
import com.atguigu.front.mapper.EduTeacherMapper;
import com.atguigu.front.service.CourseFrontService;
import com.atguigu.front.vo.CourseDetailVo;
import com.atguigu.front.vo.CoursePageVo;
import com.atguigu.front.vo.param.CourseFrontParam;
import com.atguigu.service_pojo.pojo.EduCourse;
import com.atguigu.service_pojo.pojo.EduCourseDescription;
import com.atguigu.service_pojo.pojo.EduSubject;
import com.atguigu.service_pojo.pojo.EduTeacher;
import com.atguigu.service_pojo.vo.CourseVo;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author ginga
 * @since 17/1/2023 下午2:00
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CourseFrontServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
        implements CourseFrontService {

    private final EduChapterClient chapterClient;

    private final CourseConverter courseConverter;

    private final EduTeacherMapper teacherMapper;

    private final EduCourseDescriptionMapper courseDescriptionMapper;

    private final EduSubjectMapper subjectMapper;

    private final OrderClient orderClient;

    @SneakyThrows
    @Override
    public Result list(CourseFrontParam courseFrontParam) {
        Assert.notNull(courseFrontParam, "课程列表查询参数不能为空");
        final long size = courseFrontParam.getSize();
        final long current = courseFrontParam.getCurrent();
        final Page<EduCourse> coursePage = new Page<>(current, size);
        final String buyCountSort = courseFrontParam.getBuyCountSort();
        final String gmtCreateSort = courseFrontParam.getGmtCreateSort();
        final String priceSort = courseFrontParam.getPriceSort();
        final String subjectParentId = courseFrontParam.getSubjectParentId();
        final String subjectId = courseFrontParam.getSubjectId();

        this.lambdaQuery()
            .eq(StringUtils.isNotBlank(subjectParentId), EduCourse::getSubjectParentId, subjectParentId) // 一级分类
            .eq(StringUtils.isNotBlank(subjectId), EduCourse::getSubjectId, subjectId) // 二级分类
            .orderBy(StringUtils.isNotBlank(buyCountSort), // 销量排序
                     StringUtils.isNotBlank(buyCountSort) && Integer.parseInt(buyCountSort) == 0,
                     EduCourse::getBuyCount)
            .orderBy(StringUtils.isNotBlank(gmtCreateSort), // 最新日期排序
                     StringUtils.isNotBlank(gmtCreateSort) && Integer.parseInt(gmtCreateSort) == 0,
                     EduCourse::getGmtCreate)
            .orderBy(StringUtils.isNotBlank(priceSort), // 价格排序
                     StringUtils.isNotBlank(priceSort) && Integer.parseInt(priceSort) == 0,
                     EduCourse::getPrice)
            .page(coursePage);

        final CoursePageVo coursePageVo = new CoursePageVo();
        BeanUtils.copyProperties(coursePageVo, coursePage);
        coursePageVo.setHasNext(coursePage.hasNext());
        coursePageVo.setHasPrevious(coursePage.hasPrevious());

        return Result.success(coursePageVo);
    }

    @Override
    public Result detail(String id) {
        if (StringUtils.isBlank(id)) {
            return Result.fail(ErrorInfo.PARAMS_ARE_BLANK);
        }
        final LambdaQueryChainWrapper<EduTeacher> teacherQueryWrapper = new LambdaQueryChainWrapper<>(teacherMapper);
        final LambdaQueryChainWrapper<EduCourseDescription> courseDescriptionQueryWrapper = new LambdaQueryChainWrapper<>(courseDescriptionMapper);
        final LambdaQueryChainWrapper<EduSubject> subjectQueryWrapper = new LambdaQueryChainWrapper<>(subjectMapper);
        final LambdaQueryChainWrapper<EduSubject> subjectParentQueryWrapper = new LambdaQueryChainWrapper<>(subjectMapper);

        // 1 查询课程信息
        final EduCourse course = this.getById(id);
        final EduTeacher teacher = teacherQueryWrapper.select(EduTeacher::getName, EduTeacher::getAvatar)
                                                      .eq(EduTeacher::getId, course.getTeacherId())
                                                      .one();
        final EduCourseDescription courseDescription = courseDescriptionQueryWrapper.select(EduCourseDescription::getDescription)
                                                                                    .eq(EduCourseDescription::getId, id)
                                                                                    .one();
        final EduSubject subjectLevelTwo = subjectQueryWrapper.select(EduSubject::getTitle)
                                                              .eq(EduSubject::getId, course.getSubjectId())
                                                              .one();
        final EduSubject subjectLevelOne = subjectParentQueryWrapper.select(EduSubject::getTitle)
                                                                    .eq(EduSubject::getId, course.getSubjectParentId())
                                                                    .one();
        // 2 查询课程的章节和小节
        final Result result = chapterClient.listCourseOutline(id);
        final Object chapterVideoList = result.getData();
        // 3 封装课程信息和章节小节信息
        final CourseVo courseVo = courseConverter.toCourseVo(course, courseDescription, teacher,
                                                             subjectLevelOne, subjectLevelTwo);
        // 4 返回课程信息和章节小节信息
        final CourseDetailVo courseDetailVo = new CourseDetailVo();
        courseDetailVo.setCourseWebVo(courseVo);
        courseDetailVo.setChapterVideoList(chapterVideoList);

        return Result.success(courseDetailVo);
    }

    @Override
    public Result status(String courseId, String token) {

        if (StringUtils.isAnyBlank(courseId, token)) {
            return Result.fail(ErrorInfo.PARAMS_ARE_BLANK);
        }

        final Optional<String> memberIdOpt = JWTUtils.getMemberIdByToken(token);
        if (!memberIdOpt.isPresent()) {
            return Result.fail(ErrorInfo.TOKEN_IS_INVALID);
        }

        final Boolean isBuy = orderClient.status(courseId, memberIdOpt.get());

        return Result.success(isBuy);
    }
}
