package com.atguigu.service_edu.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.service_base.aop.cache.Cache;
import com.atguigu.service_base.aop.cache.ClearCache;
import com.atguigu.service_edu.converter.EduSubjectConverter;
import com.atguigu.service_edu.mapper.EduSubjectMapper;
import com.atguigu.service_edu.service.EduSubjectService;
import com.atguigu.service_edu.vo.EduSubjectVO;
import com.atguigu.service_edu.vo.param.ExcelSubjectParam;
import com.atguigu.service_pojo.pojo.EduSubject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.DAYS;

/**
 * @author 17400
 * @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
 * @createDate 2023-01-06 16:06:47
 */
@Service
@RequiredArgsConstructor
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject>
        implements EduSubjectService {

    private final EduSubjectMapper eduSubjectMapper;

    private final EduSubjectConverter eduSubjectConverter;

    @ClearCache("listAll")
    @Override
    public void saveBatch(@NotNull List<ExcelSubjectParam> excelSubjectParamList) {
        for (ExcelSubjectParam excelSubjectParam : excelSubjectParamList) {
            final String firstLevelTitle = excelSubjectParam.getFirstLevelTitle();
            final String secondLevelTitle = excelSubjectParam.getSecondLevelTitle();
            // 1. 判断一级分类是否存在
            EduSubject firstLevelSubject = eduSubjectMapper.selectOneSubjectByTitleAndParentId(firstLevelTitle,
                                                                                               "0");
            // 2. 如果不存在，添加一级分类
            if (firstLevelSubject == null) {
                firstLevelSubject = EduSubject.builder()
                                              .title(firstLevelTitle)
                                              .parentId("0")
                                              .build();
                this.save(firstLevelSubject);
            }

            // 3. 判断二级分类是否存在
            final String firstLevelSubjectId = firstLevelSubject.getId();
            EduSubject secondLevelSubject = eduSubjectMapper.selectOneSubjectByTitleAndParentId(secondLevelTitle,
                                                                                                firstLevelSubjectId);
            // 4. 如果不存在，添加二级分类
            if (secondLevelSubject == null) {
                secondLevelSubject = EduSubject.builder()
                                               .title(secondLevelTitle)
                                               .parentId(firstLevelSubjectId)
                                               .build();
                this.save(secondLevelSubject);
            }
        }
    }

    @Cache(timeout = 1, timeUnit = DAYS)
    @Override
    public Result listAll() {
        List<EduSubjectVO> eduSubjectVOList = new ArrayList<>();
        // 1. 查询所有一级和二级分类
        final List<EduSubject> firstLevelSubjects = lambdaQuery().eq(EduSubject::getParentId, "0")
                                                                 .list();
        final List<EduSubject> allSecondLevelSubjects = lambdaQuery().ne(EduSubject::getParentId, "0")
                                                                     .list();
        // 2. 查询一级分类所拥有的所有二级分类
        for (EduSubject firstLevelSubject : firstLevelSubjects) {
            final String firstLevelSubjectId = firstLevelSubject.getId();

            final List<EduSubject> secondLevelSubjects =
                    allSecondLevelSubjects.stream()
                                          .filter(secondLevelSubject -> StringUtils.equals(firstLevelSubjectId,
                                                                                           secondLevelSubject.getParentId()))
                                          .collect(Collectors.toList());

            // 2.1 将一级分类和它所有的二级分类封装到EduSubjectVO对象中
            final EduSubjectVO eduSubjectVO = eduSubjectConverter.toEduSubjectVO(firstLevelSubject, secondLevelSubjects);
            // 2.2 将EduSubjectVO对象添加到EduSubjectVO对象列表中
            eduSubjectVOList.add(eduSubjectVO);
        }

        // 3. 返回EduSubjectVO对象列表, 或者空列表
        return Result.success(eduSubjectVOList);
    }

}




