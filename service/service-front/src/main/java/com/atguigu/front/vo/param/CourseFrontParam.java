package com.atguigu.front.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ginga
 * @since 17/1/2023 下午1:55
 */
@ApiModel("课程列表参数")
@Data
public class CourseFrontParam {

    @ApiModelProperty("当前页")
    private long current;

    @ApiModelProperty("每页记录数")
    private long size;

    @ApiModelProperty("课程名称")
    private String title;

    @ApiModelProperty("讲师id")
    private String teacherId;

    @ApiModelProperty("一级分类id")
    private String subjectParentId;

    @ApiModelProperty("二级分类id")
    private String subjectId;

    @ApiModelProperty("销量排序")
    private String buyCountSort;

    @ApiModelProperty("最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty("价格排序")
    private String priceSort;
}
