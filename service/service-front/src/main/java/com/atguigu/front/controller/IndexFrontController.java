package com.atguigu.front.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.front.service.IndexFrontService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginga
 * @since 14/1/2023 下午4:32
 */
@Api(tags = "首页数据展示")
@RequestMapping("edu-front/index-front")
@RestController
@RequiredArgsConstructor
public class IndexFrontController {

    private final IndexFrontService indexFrontService;

    @GetMapping("index")
    public Result index() {
        return indexFrontService.index();
    }
}
