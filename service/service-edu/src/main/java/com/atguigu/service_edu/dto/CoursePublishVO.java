package com.atguigu.service_edu.dto;

import lombok.Data;

/**
 * @author ginga
 * @since 10/1/2023 上午12:04
 */
@Data
public class CoursePublishVO {
    private String id;
    private String title;
    private String price;
    private Integer lessonNum;
    private String teacherName;
    private String firstLevelTitle;
    private String secondLevelTitle;
    private String cover;
}
