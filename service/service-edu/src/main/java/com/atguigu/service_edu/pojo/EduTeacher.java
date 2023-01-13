package com.atguigu.service_edu.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 讲师
 *
 * @TableName edu_teacher
 */
@TableName(value = "edu_teacher")
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "讲师")
public class EduTeacher implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 讲师ID
     */
    @ApiModelProperty(value = "讲师ID")
    @TableId
    private String id;

    /**
     * 讲师姓名
     */
    @ApiModelProperty(value = "讲师姓名")
    private String name;

    /**
     * 讲师简介
     */
    @ApiModelProperty(value = "讲师简介")
    private String intro;

    /**
     * 讲师资历,一句话说明讲师
     */
    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;

    /**
     * 头衔 1高级讲师 2首席讲师
     */
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    /**
     * 讲师头像
     */
    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    private Integer deleted;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

}