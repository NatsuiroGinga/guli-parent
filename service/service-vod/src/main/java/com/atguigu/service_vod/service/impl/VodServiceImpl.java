package com.atguigu.service_vod.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.service_base.aop.statistic.Statistic;
import com.atguigu.service_base.exceptionhandler.exception.GuliException;
import com.atguigu.service_vod.manager.VodManager;
import com.atguigu.service_vod.service.VodService;
import com.atguigu.service_vod.vo.VideoAuthVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.atguigu.common_utils.ErrorInfo.PARAMS_ARE_BLANK;
import static com.atguigu.common_utils.ErrorInfo.UPLOAD_VIDEO_ERROR;

/**
 * @author ginga
 * @since 12/1/2023 下午10:24
 */
@Service
@RequiredArgsConstructor
public class VodServiceImpl implements VodService {

    private final VodManager vodManager;

    @Override
    public Result upload(@NotNull MultipartFile file) {
        final String filename = file.getOriginalFilename();
        final String title = StringUtils.substringBeforeLast(filename, ".");
        String videoId;

        try {
            videoId = vodManager.uploadStream(title, filename, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return Result.fail(UPLOAD_VIDEO_ERROR);
        }

        return Result.success(videoId);
    }

    @Override
    public Result delete(String id) {
        try {
            vodManager.deleteVideo(id);
        } catch (GuliException e) {
            e.printStackTrace();
            return Result.fail(e.getCode(), e.getMsg());
        }
        return Result.success();
    }

    @Override
    public Result deleteBatch(@NotNull List<String> videoIdList) {

        if (videoIdList.isEmpty()) {
            return Result.fail(PARAMS_ARE_BLANK);
        }

        // 1 将id集合拼接为字符串
        final String videoIds = StringUtils.join(videoIdList, ",");
        // 2 删除
        try {
            vodManager.deleteVideo(videoIds);
        } catch (GuliException e) {
            e.printStackTrace();
            return Result.fail(e.getCode(), e.getMsg());
        }

        return Result.success();
    }

    @Statistic
    @Override
    public Result getAuth(String id) {
        try {
            final VideoAuthVo videoAuthVo = vodManager.getAuthById(id);
            return Result.success(videoAuthVo);
        } catch (GuliException e) {
            return Result.fail(e.getCode(), e.getMsg());
        }
    }
}
