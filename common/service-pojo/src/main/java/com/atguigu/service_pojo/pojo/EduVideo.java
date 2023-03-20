package com.atguigu.service_pojo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 课程视频
 *
 * @TableName edu_video
 */
@TableName(value = "edu_video")
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EduVideo implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 9L;
    /**
     * 视频ID
     */
    @TableId
    private String id;
    /**
     * 课程ID
     */
    private String courseId;
    /**
     * 章节ID
     */
    private String chapterId;
    /**
     * 节点名称
     */
    private String title;
    /**
     * 云端视频资源
     */
    private String videoSourceId;
    /**
     * 原始文件名称
     */
    private String videoOriginalName;
    /**
     * 排序字段
     */
    private Integer sort;
    /**
     * 播放次数
     */
    private Long playCount;
    /**
     * 是否可以试听：0收费 1免费
     */
    @TableField("is_free")
    private Integer free;
    /**
     * 视频时长（秒）
     */
    private Double duration;
    /**
     * Empty未上传 Transcoding转码中  Normal正常
     */
    private String status;
    /**
     * 视频源文件大小（字节）
     */
    private Long size;
    /**
     * 乐观锁
     */
    @Version
    private Long version;

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