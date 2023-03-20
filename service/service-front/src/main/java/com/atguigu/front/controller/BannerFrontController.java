package com.atguigu.front.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.front.service.CrmBannerFrontService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginga
 * @since 14/1/2023 下午4:24
 */
@Api(tags = "首页管理前台")
@RequestMapping("edu-front/banner-front")
@RestController
@RequiredArgsConstructor
public class BannerFrontController {

    private final CrmBannerFrontService bannerService;

    @ApiOperation("查询所有banner数据")
    @GetMapping("list")
    public Result listAll() {
        return bannerService.listAll();
    }

}
