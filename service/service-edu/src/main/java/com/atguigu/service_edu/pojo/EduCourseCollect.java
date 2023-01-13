package com.atguigu.service_edu.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程收藏
 *
 * @TableName edu_course_collect
 */
@TableName(value = "edu_course_collect")
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EduCourseCollect implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 5L;
    /**
     * 收藏ID
     */
    @TableId
    private String id;
    /**
     * 课程讲师ID
     */
    private String courseId;
    /**
     * 课程专业ID
     */
    private String memberId;
    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
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