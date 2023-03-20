package com.atguigu.front.vo;

import com.atguigu.service_pojo.vo.CourseVo;
import lombok.Data;

/**
 * @author ginga
 * @since 17/1/2023 下午3:13
 */
@Data
public class CourseDetailVo {
    private CourseVo courseWebVo;
    private Object chapterVideoList;
    private boolean isBuy;
}
