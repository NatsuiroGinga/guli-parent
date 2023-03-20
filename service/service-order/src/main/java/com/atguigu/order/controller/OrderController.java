package com.atguigu.order.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.order.service.OrderService;
import com.atguigu.service_pojo.vo.param.QueryOrderParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author ginga
 * @since 17/1/2023 下午10:35
 */
@Api(tags = "订单管理")
@RequestMapping("edu-order/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @ApiOperation("创建订单")
    @PostMapping("{courseId}")
    public Result add(@PathVariable String courseId, @RequestHeader("Authorization") String token) {
        return orderService.add(courseId, token);
    }

    @ApiOperation("查询订单")
    @GetMapping("{orderNo}")
    public Result detail(@PathVariable String orderNo) {
        return orderService.detail(orderNo);
    }

    @ApiOperation("查询订单状态")
    @GetMapping("status")
    public Boolean status(@RequestParam("courseId") String courseId, @RequestParam("memberId") String memberId) {
        final QueryOrderParam queryOrderParam = new QueryOrderParam();
        queryOrderParam.setCourseId(courseId);
        queryOrderParam.setMemberId(memberId);
        return orderService.getOrderStatus(queryOrderParam);
    }

}
