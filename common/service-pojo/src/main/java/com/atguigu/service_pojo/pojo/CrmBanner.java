package com.atguigu.service_pojo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE;

/**
 * 首页banner表
 *
 * @TableName crm_banner
 */
@TableName(value = "crm_banner")
@Data
public class CrmBanner implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 11L;
    /**
     * ID
     */
    @TableId
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 链接地址
     */
    private String linkUrl;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableField("is_deleted")
    private Integer deleted;
    /**
     * 创建时间
     */
    @TableField(fill = INSERT)
    private LocalDateTime gmtCreate;
    /**
     * 更新时间
     */
    @TableField(fill = INSERT_UPDATE)
    private LocalDateTime gmtModified;
}