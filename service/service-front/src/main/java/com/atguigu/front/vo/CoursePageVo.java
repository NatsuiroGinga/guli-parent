package com.atguigu.front.vo;

import com.atguigu.service_pojo.vo.CourseVo;
import lombok.Data;

import java.util.List;

/**
 * @author ginga
 * @since 17/1/2023 下午2:19
 */
@Data
public class CoursePageVo {
    private long current;
    private long size;
    private long total;
    private long pages;
    private boolean hasNext;
    private boolean hasPrevious;
    private List<CourseVo> records;
}
