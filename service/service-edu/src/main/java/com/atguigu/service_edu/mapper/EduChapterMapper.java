package com.atguigu.service_edu.mapper;

import com.atguigu.service_pojo.pojo.EduChapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 17400
 * @description 针对表【edu_chapter(课程章节)】的数据库操作Mapper
 * @createDate 2023-01-07 13:59:11
 * @Entity com.atguigu.service_edu.pojo.EduChapter
 */
public interface EduChapterMapper extends BaseMapper<EduChapter> {

    boolean deleteByCourseId(@Param("courseId") String courseId);
}




