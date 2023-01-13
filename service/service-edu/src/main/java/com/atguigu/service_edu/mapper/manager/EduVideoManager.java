package com.atguigu.service_edu.mapper.manager;

import com.atguigu.service_edu.client.VodClient;
import com.atguigu.service_edu.mapper.EduVideoMapper;
import com.atguigu.service_edu.pojo.EduVideo;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ginga
 * @since 13/1/2023 下午4:23
 */
@Component
@RequiredArgsConstructor
public class EduVideoManager {

    private final EduVideoMapper videoMapper;

    private final VodClient vodClient;

    public void deleteVideoByCourseId(String courseId) {
        // 1 根据课程id查询课程的所有视频id
        final LambdaQueryChainWrapper<EduVideo> queryChainWrapper = new LambdaQueryChainWrapper<>(videoMapper);
        final LambdaUpdateChainWrapper<EduVideo> updateChainWrapper = new LambdaUpdateChainWrapper<>(videoMapper);
        final List<EduVideo> videoList = queryChainWrapper.eq(EduVideo::getCourseId, courseId)
                                                          .select(EduVideo::getVideoSourceId)
                                                          .list();
        final List<String> videoIdList = videoList.stream()
                                                  .map(EduVideo::getVideoSourceId) // 获取视频id
                                                  .filter(StringUtils::isNotBlank) // 过滤掉空字符串
                                                  .collect(Collectors.toList());
        // 2 根据视频id删除视频
        if (videoIdList.isEmpty()) {
            return;
        }
        vodClient.deleteBatch(videoIdList);

        // 3 根据课程id删除课程的所有小节
        updateChainWrapper.eq(EduVideo::getCourseId, courseId)
                          .remove();

    }

}
