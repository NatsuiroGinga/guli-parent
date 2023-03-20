package com.atguigu.order.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.order.service.PayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author ginga
 * @since 19/1/2023 下午11:21
 */
@Api(tags = "支付日志")
@RequestMapping("edu-order/pay-log")
@RestController
@RequiredArgsConstructor
public class PayLogController {

    private final PayLogService payLogService;

    //参数是订单号
    @ApiOperation("生成微信支付二维码")
    @PostMapping("{orderNo}")
    public Map createNative(@PathVariable String orderNo) {
        //返回信息，包含二维码地址，还有其他需要的信息
        return payLogService.createNatvie(orderNo);
    }

    @ApiOperation("查询订单支付状态")
    @GetMapping("{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo) {
        Map map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:" + map);
        if (map == null) {
            return Result.fail(20001, "订单出错");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if (map.get("trade_state").equals("SUCCESS")) {//支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return Result.success();
        }

        return Result.fail(20001, "支付中");
    }

}
