package com.atguigu.service_edu.client;

import com.atguigu.common_utils.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ginga
 * @since 13/1/2023 下午2:34
 */
@FeignClient(value = "service-vod", path = "/edu-vod/video", fallback = VodFileDegradeFeignClient.class)
public interface VodClient {

    /**
     * 删除阿里云视频
     *
     * @param id 视频id
     */
    @DeleteMapping("{id}")
    Result delete(@PathVariable("id") String id);

    /**
     * 批量删除视频
     *
     * @param videoIdList 视频id集合
     */
    @DeleteMapping("batch")
    Result deleteBatch(@ApiParam("视频id集合")
                       @RequestParam("videoIdList") List<String> videoIdList);

}
