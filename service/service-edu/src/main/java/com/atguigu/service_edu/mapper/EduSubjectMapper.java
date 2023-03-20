package com.atguigu.service_edu.mapper;

import com.atguigu.service_pojo.pojo.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 17400
 * @description 针对表【edu_subject(课程科目)】的数据库操作Mapper
 * @createDate 2023-01-06 16:06:47
 * @Entity com.atguigu.service_edu.pojo.EduSubject
 */
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

    EduSubject selectOneSubjectByTitleAndParentId(@Param("title") String title,
                                                  @Param("parentId") String parentId);

}




