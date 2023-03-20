package com.atguigu.order.service;

import com.atguigu.common_utils.Result;
import com.atguigu.service_pojo.pojo.Order;
import com.atguigu.service_pojo.vo.param.QueryOrderParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ginga
 * @since 17/1/2023 下午10:31
 */
public interface OrderService extends IService<Order> {
    /**
     * 创建订单
     *
     * @param courseId 课程id
     * @param token    用户token
     * @return 订单号
     */
    Result add(String courseId, String token);

    /**
     * 查询订单
     *
     * @param orderNo 订单号
     * @return 订单信息
     */
    Result detail(String orderNo);

    /**
     * 查询订单状态
     *
     * @param queryOrderParam 订单查询参数
     * @return 订单状态, 是否支付
     */
    Boolean getOrderStatus(QueryOrderParam queryOrderParam);
}
