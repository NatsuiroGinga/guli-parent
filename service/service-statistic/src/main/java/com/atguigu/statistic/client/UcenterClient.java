package com.atguigu.statistic.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author ginga
 * @since 20/1/2023 下午1:26
 */
@FeignClient(value = "service-ucenter", path = "edu-ucenter/member")
public interface UcenterClient {

    @GetMapping("statistic/register/{date}")
    Optional<Long> countRegister(@PathVariable("date") String date);
}
