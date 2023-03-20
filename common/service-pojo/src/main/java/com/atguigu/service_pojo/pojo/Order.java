package com.atguigu.service_pojo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE;

/**
 * 订单
 *
 * @TableName t_order
 */
@TableName(value = "t_order")
@Data
public class Order implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 12L;
    /**
     *
     */
    @TableId
    private String id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 课程id
     */
    private String courseId;
    /**
     * 课程名称
     */
    private String courseTitle;
    /**
     * 课程封面
     */
    private String courseCover;
    /**
     * 讲师名称
     */
    private String teacherName;
    /**
     * 会员id
     */
    private String memberId;
    /**
     * 会员昵称
     */
    private String nickname;
    /**
     * 会员手机
     */
    private String mobile;
    /**
     * 订单金额（分）
     */
    private BigDecimal totalFee;
    /**
     * 支付类型（1：微信 2：支付宝）
     */
    private Integer payType;
    /**
     * 订单状态（0：未支付 1：已支付）
     */
    private Integer status;

    @Version
    private Long version;

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