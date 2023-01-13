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
 * 课程科目
 *
 * @TableName edu_subject
 */
@TableName(value = "edu_subject")
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "课程科目", description = "课程科目")
public class EduSubject implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 2L;

    /**
     * 课程类别ID
     */
    @ApiModelProperty(value = "课程类别ID")
    @TableId
    private String id;
    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称")
    private String title;
    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    private String parentId;
    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

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