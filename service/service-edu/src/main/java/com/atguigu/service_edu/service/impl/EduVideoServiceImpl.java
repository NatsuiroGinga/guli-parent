package com.atguigu.service_edu.service.impl;

import com.atguigu.common_utils.ErrorInfo;
import com.atguigu.common_utils.Result;
import com.atguigu.service_base.aop.cache.ClearCache;
import com.atguigu.service_edu.client.VodClient;
import com.atguigu.service_edu.converter.EduVideoConverter;
import com.atguigu.service_edu.mapper.EduVideoMapper;
import com.atguigu.service_edu.pojo.EduVideo;
import com.atguigu.service_edu.service.EduVideoService;
import com.atguigu.service_edu.vo.param.VideoParam;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.atguigu.common_utils.ErrorInfo.*;

/**
 * @author 17400
 * @description 针对表【edu_video(课程视频)】的数据库操作Service实现
 * @createDate 2023-01-07 13:59:11
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo>
        implements EduVideoService {

    private final EduVideoConverter videoConverter;

    private final VodClient vodClient;

    @ClearCache(value = "listCourseOutline", target = EduChapterServiceImpl.class)
    @Override
    public Result add(@NotNull VideoParam videoParam) {
        // 判断参数非空
        final String title = videoParam.getTitle();
        if (StringUtils.isBlank(title)) {
            return Result.fail(PARAMS_ARE_BLANK);
        }

        final EduVideo video = videoConverter.toEduVideo(videoParam);
        return this.save(video) ? Result.success() : Result.fail(ErrorInfo.ADD_VIDEO_ERROR);
    }

    @ClearCache(value = "listCourseOutline", target = EduChapterServiceImpl.class)
    @Transactional
    @Override
    public Result removeVideoById(String id) {
        // 1. 根据小节id获取视频id
        final EduVideo video = this.getById(id);
        if (video == null) {
            return Result.fail(ITEM_NOT_FOUND);
        }
        final String videoSourceId = video.getVideoSourceId();
        // 2. 删除视频
        if (StringUtils.isNotBlank(videoSourceId)) {
            vodClient.delete(videoSourceId);
        }
        return this.removeById(id) ? Result.success() : Result.fail(DELETE_VIDEO_ERROR);
    }
}




