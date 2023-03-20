package com.atguigu.service_pojo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT;
import static com.baomidou.mybatisplus.annotation.FieldFill.INSERT_UPDATE;

/**
 * 支付日志表
 *
 * @TableName t_pay_log
 */
@TableName(value = "t_pay_log")
@Data
public class PayLog implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 13L;
    /**
     * id
     */
    @TableId
    private String id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 支付完成时间
     */
    private LocalDateTime payTime;
    /**
     * 支付金额（分）
     */
    private BigDecimal totalFee;
    /**
     * 交易流水号
     */
    private String transactionId;
    /**
     * 交易状态
     */
    private String tradeState;
    /**
     * 支付类型（1：微信 2：支付宝）
     */
    private Integer payType;
    /**
     * 其他属性
     */
    private String attr;
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