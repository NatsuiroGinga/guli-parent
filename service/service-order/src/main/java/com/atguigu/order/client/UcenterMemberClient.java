package com.atguigu.order.client;

import com.atguigu.service_pojo.pojo.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author ginga
 * @since 19/1/2023 下午8:57
 */
@FeignClient(value = "service-ucenter", path = "edu-ucenter/member")
public interface UcenterMemberClient {

    @GetMapping("{id}")
    Optional<UcenterMember> getMemberInfo(@PathVariable("id") String id);
}
