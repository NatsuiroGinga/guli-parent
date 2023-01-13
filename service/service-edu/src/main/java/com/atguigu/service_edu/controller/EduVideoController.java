package com.atguigu.service_edu.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.service.EduVideoService;
import com.atguigu.service_edu.vo.param.VideoParam;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author ginga
 * @since 8/1/2023 下午11:35
 */
@Api(tags = "课程小节管理")
@RestController
@RequestMapping("edu-service/video")
@RequiredArgsConstructor
public class EduVideoController {

    private final EduVideoService videoService;

    @PostMapping
    public Result add(@RequestBody VideoParam videoParam) {
        return videoService.add(videoParam);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        return videoService.removeVideoById(id);
    }
}
