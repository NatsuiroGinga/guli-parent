package com.atguigu.order.service;

import com.atguigu.service_pojo.pojo.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author ginga
 * @since 17/1/2023 下午10:32
 */
public interface PayLogService extends IService<PayLog> {
    /**
     * 生成微信支付二维码接口
     *
     * @param orderNo 订单号
     * @return 二维码地址
     */
    Map createNatvie(String orderNo);

    /**
     * 查询订单支付状态
     *
     * @param orderNo 订单号
     * @return 订单状态
     */
    Map queryPayStatus(String orderNo);

    /**
     * 异步更新订单状态
     *
     * @param map 支付成功后的回调信息
     */
    void updateOrdersStatus(Map<String, String> map);

}
