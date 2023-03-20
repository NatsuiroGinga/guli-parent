package com.atguigu.service_edu.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.converter.EduChapterConverter;
import com.atguigu.service_edu.converter.EduVideoConverter;
import com.atguigu.service_edu.mapper.EduChapterMapper;
import com.atguigu.service_edu.mapper.EduVideoMapper;
import com.atguigu.service_edu.service.EduChapterService;
import com.atguigu.service_edu.vo.EduChapterVO;
import com.atguigu.service_edu.vo.EduVideoVO;
import com.atguigu.service_edu.vo.param.ChapterParam;
import com.atguigu.service_pojo.pojo.EduChapter;
import com.atguigu.service_pojo.pojo.EduVideo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.atguigu.common_utils.ErrorInfo.*;

/**
 * @author 17400
 * @description 针对表【edu_chapter(课程章节)】的数据库操作Service实现
 * @createDate 2023-01-07 13:59:11
 */
@Service
@RequiredArgsConstructor
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter>
        implements EduChapterService {

    private final EduVideoMapper videoMapper;

    private final EduChapterConverter chapterConverter;

    private final EduVideoConverter videoConverter;

    @Override
    public Result listCourseOutline(String courseId) {
        // 1. 根据课程id查询课程章节
        final List<EduChapter> chapterList = lambdaQuery().eq(EduChapter::getCourseId, courseId)
                                                          .list();
        // 2. 根据课程id查询课程小节
        final List<EduVideo> videoList = videoMapper.selectListByCourseId(courseId);

        // 3. 将课程小节放入课程章节中
        List<EduChapterVO> chapterVOList = new ArrayList<>();
        for (EduChapter chapter : chapterList) {
            // 3.1 将课程小节转换为课程小节视图对象
            final List<EduVideoVO> children = videoList.stream()
                                                       // 3.1.1 过滤出当前章节的小节
                                                       .filter(video -> StringUtils.equals(video.getChapterId(), chapter.getId()))
                                                       // 3.1.2 将小节转换为小节视图对象
                                                       .map(videoConverter::toEduVideoVO)
                                                       .collect(Collectors.toList());
            // 3.2 将课程章节转换为课程章节视图对象
            final EduChapterVO chapterVO = chapterConverter.toEduChapterVO(chapter, children);
            chapterVOList.add(chapterVO);
        }

        // 4. 返回课程章节视图对象列表, 如果没有查询到, 返回空列表
        return Result.success(chapterVOList);
    }

    @Override
    public Result add(@NotNull ChapterParam chapterParam) {
        final String title = chapterParam.getTitle();
        if (StringUtils.isBlank(title)) {
            return Result.fail(PARAMS_ARE_BLANK);
        }
        final EduChapter chapter = chapterConverter.toEduChapter(chapterParam);
        return this.save(chapter) ? Result.success() : Result.fail(ADD_CHAPTER_ERROR);
    }

    @Override
    public Result updateById(@NotNull ChapterParam chapterParam) {
        final String title = chapterParam.getTitle();
        if (StringUtils.isBlank(title)) {
            return Result.fail(PARAMS_ARE_BLANK);
        }
        final EduChapter chapter = chapterConverter.toEduChapter(chapterParam);
        return this.updateById(chapter) ? Result.success() : Result.fail(UPDATE_CHAPTER_ERROR);
    }
}




