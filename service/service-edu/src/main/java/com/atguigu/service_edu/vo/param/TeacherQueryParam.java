package com.atguigu.service_edu.vo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ginga
 * @version 1.0
 * @ClassName TeacherQueryParam
 * @Date 20/9/2022 下午6:19
 */
@Data
public class TeacherQueryParam {

    @ApiModelProperty("教师名, 模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔1 高级讲师 2首席讲师", example = "1")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2019-10-30 10:51:20")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-11-1 17:45:13")
    private String end;

}
