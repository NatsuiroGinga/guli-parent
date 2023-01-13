package com.atguigu.service_edu.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 课程基本信息
 *
 * @TableName edu_course
 */
@TableName(value = "edu_course")
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EduCourse implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 4L;
    /**
     * 课程ID
     */
    @TableId
    private String id;
    /**
     * 课程讲师ID
     */
    private String teacherId;
    /**
     * 课程专业ID
     */
    private String subjectId;
    /**
     * 课程专业父级ID
     */
    private String subjectParentId;
    /**
     * 课程标题
     */
    private String title;
    /**
     * 课程销售价格，设置为0则可免费观看
     */
    private BigDecimal price;
    /**
     * 总课时
     */
    private Object lessonNum;
    /**
     * 课程封面图片路径
     */
    private String cover;
    /**
     * 销售数量
     */
    private Long buyCount;
    /**
     * 浏览数量
     */
    private Long viewCount;
    /**
     * 乐观锁
     */
    @Version
    private Long version;
    /**
     * 课程状态 Draft未发布  Normal已发布
     */
    private String status;
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