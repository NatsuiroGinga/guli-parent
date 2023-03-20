package com.atguigu.order.client;

import com.atguigu.service_pojo.vo.CourseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

/**
 * @author ginga
 * @since 19/1/2023 下午8:56
 */
@FeignClient(value = "service-front", path = "edu-front/course")
public interface CourseFrontClient {

    @GetMapping("order/{courseId}")
    Optional<CourseVo> order(@PathVariable("courseId") String courseId);
}
