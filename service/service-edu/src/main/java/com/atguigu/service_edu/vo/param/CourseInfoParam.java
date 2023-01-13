package com.atguigu.service_edu.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ginga
 * @since 7/1/2023 下午2:24
 */
@ApiModel(description = "课程信息")
@Data
public class CourseInfoParam {

    @ApiModelProperty("课程讲师ID")
    private String teacherId;

    @ApiModelProperty("课程专业ID")
    private String subjectId;

    @ApiModelProperty("课程专业父级ID")
    private String subjectParentId;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程价格, 为0可以免费观看")
    private BigDecimal price;

    @ApiModelProperty("总课时")
    private Integer lessonNum;

    @ApiModelProperty("课程封面图片路径")
    private String cover;

    @ApiModelProperty("课程简介")
    private String description;

}
