package com.atguigu.order.vo;

import lombok.Data;

/**
 * @author ginga
 * @since 17/1/2023 下午11:03
 */
@Data
public class OrderVo {
    private String id;
    private String orderNo;
    private String courseId;
    private String courseTitle;
    private String courseCover;
    private String teacherName;
    private String memberId;
    private String nickname;
    private String mobile;
    private String totalFee;
    private Integer payType;
    private Integer status;
}
