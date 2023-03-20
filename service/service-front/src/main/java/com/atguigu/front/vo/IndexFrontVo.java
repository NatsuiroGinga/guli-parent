package com.atguigu.front.vo;

import com.atguigu.service_pojo.pojo.EduCourse;
import com.atguigu.service_pojo.pojo.EduTeacher;
import lombok.Data;

import java.util.List;

/**
 * @author ginga
 * @since 14/1/2023 下午5:00
 */
@Data
public class IndexFrontVo {
    private List<EduTeacher> teacherList;
    private List<EduCourse> courseList;
}
