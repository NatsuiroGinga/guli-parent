package com.atguigu.front.client;

import com.atguigu.common_utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ginga
 * @since 17/1/2023 下午3:09
 */
@FeignClient(value = "service-edu", path = "/edu-service/chapter")
public interface EduChapterClient {

    @GetMapping("course-outline/{courseId}")
    Result listCourseOutline(@PathVariable String courseId);
}
