package com.atguigu.service_cms.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.service_cms.service.CrmBannerService;
import com.atguigu.service_cms.vo.param.BannerPageParam;
import com.atguigu.service_pojo.pojo.CrmBanner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author ginga
 * @since 14/1/2023 下午3:03
 */
@Api(tags = "首页管理后台")
@RequestMapping("service-cms/banner-admin")
@RequiredArgsConstructor
@RestController
public class BannerAdminController {

    private final CrmBannerService bannerService;

    @ApiOperation("分页查询")
    @PostMapping("list")
    public Result listPages(@RequestBody BannerPageParam pageParam) {
        return bannerService.listPages(pageParam);
    }

    @ApiOperation("增加")
    @PostMapping
    public Result add(@RequestBody CrmBanner banner) {
        bannerService.save(banner);
        return Result.success();
    }

    @ApiOperation("修改")
    @PutMapping
    public Result update(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return Result.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        bannerService.removeById(id);
        return Result.success();
    }
}
