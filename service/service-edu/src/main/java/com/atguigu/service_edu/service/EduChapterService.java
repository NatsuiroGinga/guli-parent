package com.atguigu.service_edu.service;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.pojo.EduChapter;
import com.atguigu.service_edu.vo.param.ChapterParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 17400
 * @description 针对表【edu_chapter(课程章节)】的数据库操作Service
 * @createDate 2023-01-07 13:59:11
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 根据课程id查询课程大纲
     *
     * @param courseId 课程id
     */
    Result listCourseOutline(String courseId);

    /**
     * 添加章节
     *
     * @param chapterParam 章节参数
     */
    Result add(ChapterParam chapterParam);

    /**
     * 修改章节
     *
     * @param chapterParam 章节参数
     */
    Result updateById(ChapterParam chapterParam);
}
