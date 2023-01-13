package com.atguigu.service_vod.service;

import com.atguigu.common_utils.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author ginga
 * @since 12/1/2023 下午10:23
 */
public interface VodService {

    /**
     * 上传视频到阿里云
     *
     * @param file 视频文件
     */
    Result upload(MultipartFile file);

    /**
     * 删除阿里云视频
     *
     * @param id 视频id
     */
    Result delete(String id);

    /**
     * 批量删除视频
     *
     * @param videoIdList 视频id集合
     */
    Result deleteBatch(List<String> videoIdList);
}
