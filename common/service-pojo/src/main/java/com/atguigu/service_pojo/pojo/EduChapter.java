package com.atguigu.service_pojo.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程章节
 *
 * @TableName edu_chapter
 */
@TableName(value = "edu_chapter")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EduChapter implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 3L;
    /**
     * 章节ID
     */
    @TableId
    private String id;
    /**
     * 课程ID
     */
    private String courseId;
    /**
     * 章节名称
     */
    private String title;
    /**
     * 显示排序
     */
    private Integer sort;

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