package com.atguigu.service_pojo.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 网站统计日数据
 *
 * @TableName statistics_daily
 */
@TableName(value = "statistics_daily")
@Data
@Accessors(chain = true)
public class StatisticsDaily implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 20L;
    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 统计日期
     */
    private String dateCalculated;
    /**
     * 注册人数
     */
    private Long registerNum;
    /**
     * 登录人数
     */
    private Long loginNum;
    /**
     * 每日播放视频数
     */
    private Long videoViewNum;
    /**
     * 每日新增课程数
     */
    private Long courseNum;
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