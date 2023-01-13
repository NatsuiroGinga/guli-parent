package com.atguigu.service_edu.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ginga
 * @since 6/1/2023 下午5:00
 */
@ApiModel(description = "Excel导入的课程分类参数")
@Data
public class ExcelSubjectParam {

    @ApiModelProperty("一级分类名称")
    private String firstLevelTitle;

    @ApiModelProperty("二级分类名称")
    private String secondLevelTitle;
}
