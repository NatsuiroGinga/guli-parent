package com.atguigu.service_pojo.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.INPUT;

/**
 * 课程简介
 *
 * @TableName edu_course_description
 */
@TableName(value = "edu_course_description")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EduCourseDescription implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 8L;
    /**
     * 课程ID
     */
    @TableId(type = INPUT)
    private String id;
    /**
     * 课程简介
     */
    private String description;

    @TableField("is_deleted")
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
}