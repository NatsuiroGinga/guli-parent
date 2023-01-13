package com.atguigu.service_edu.mapper;

import com.atguigu.service_edu.pojo.EduVideo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 17400
 * @description 针对表【edu_video(课程视频)】的数据库操作Mapper
 * @createDate 2023-01-07 13:59:11
 * @Entity com.atguigu.service_edu.pojo.EduVideo
 */
public interface EduVideoMapper extends BaseMapper<EduVideo> {

    List<EduVideo> selectListByCourseId(@Param("courseId") String courseId);

    boolean deleteByCourseId(@Param("courseId") String courseId);
}




