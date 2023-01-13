package com.atguigu.service_vod.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.service_vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ginga
 * @since 12/1/2023 下午9:25
 */
@Api(tags = "阿里云视频点播")
@RestController
@RequestMapping("edu-vod/video")
@RequiredArgsConstructor
public class VodController {

    private final VodService vodService;

    @ApiOperation("上传视频到阿里云")
    @PostMapping
    public Result upload(@ApiParam("视频文件") MultipartFile file) {
        return vodService.upload(file);
    }

    @ApiOperation("删除阿里云视频")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable
                         @ApiParam("视频id") String id) {
        return vodService.delete(id);
    }

    @ApiOperation("批量删除视频")
    @DeleteMapping("batch")
    public Result deleteBatch(@ApiParam("视频id集合")
                              @RequestParam("videoIdList") List<String> videoIdList) {
        return vodService.deleteBatch(videoIdList);
    }

}
