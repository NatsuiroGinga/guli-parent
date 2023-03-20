package com.atguigu.service_edu.converter;

import com.atguigu.service_edu.vo.EduChapterVO;
import com.atguigu.service_edu.vo.EduVideoVO;
import com.atguigu.service_edu.vo.param.ChapterParam;
import com.atguigu.service_pojo.pojo.EduChapter;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * @author ginga
 * @since 8/1/2023 下午10:33
 */
@Mapper(componentModel = SPRING,
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = IGNORE)
public interface EduChapterConverter {

    EduChapterVO toEduChapterVO(EduChapter chapter, List<EduVideoVO> children);

    EduChapter toEduChapter(ChapterParam chapterParam);

}
