package com.atguigu.front.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ginga
 * @since 20/1/2023 上午11:52
 */
@FeignClient(value = "service-order", path = "edu-order/order")
public interface OrderClient {

    @GetMapping("status")
    Boolean status(@RequestParam("courseId") String courseId, @RequestParam("memberId") String memberId);
}
