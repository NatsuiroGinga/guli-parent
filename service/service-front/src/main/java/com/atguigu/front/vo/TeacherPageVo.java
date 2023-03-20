package com.atguigu.front.vo;

import com.atguigu.service_pojo.pojo.EduTeacher;
import lombok.Data;

import java.util.List;

/**
 * @author ginga
 * @since 17/1/2023 上午12:26
 */
@Data
public class TeacherPageVo {
    private long current;
    private long size;
    private long total;
    private long pages;
    private boolean hasNext;
    private boolean hasPrevious;
    private List<EduTeacher> records;
}
