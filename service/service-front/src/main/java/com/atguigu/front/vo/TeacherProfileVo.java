package com.atguigu.front.vo;

import com.atguigu.service_pojo.vo.CourseVo;
import lombok.Data;

import java.util.List;

/**
 * @author ginga
 * @since 17/1/2023 下午1:32
 */
@Data
public class TeacherProfileVo {
    private TeacherVo teacher;
    private List<CourseVo> courseList;
}
