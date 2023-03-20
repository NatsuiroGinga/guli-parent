package com.atguigu.service_pojo.vo;

import lombok.Data;

/**
 * @author ginga
 * @since 17/1/2023 下午1:35
 */
@Data
public class CourseVo {
    private String id;
    private String avatar;
    private String title;
    private String cover;
    private String description;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
    private String lessonNum;
    private String buyCount;
    private String viewCount;
}
