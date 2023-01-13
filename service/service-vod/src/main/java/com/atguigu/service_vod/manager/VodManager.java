package com.atguigu.service_vod.manager;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.common_utils.ErrorInfo;
import com.atguigu.service_base.exceptionhandler.exception.GuliException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.InputStream;

import static com.atguigu.service_vod.util.VODProperties.ACCESS_KEY_ID;
import static com.atguigu.service_vod.util.VODProperties.ACCESS_KEY_SECRET;

/**
 * @author ginga
 * @since 12/1/2023 下午10:25
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class VodManager {

    private final IAcsClient client;

    public String uploadStream(String title, String fileName, InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(ACCESS_KEY_ID, ACCESS_KEY_SECRET,
                                                              title, fileName, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        log.info("开始上传视频...");
        UploadStreamResponse response = uploader.uploadStream(request);
        log.info("RequestId: {}", response.getRequestId());

        String videoId = response.getVideoId();
        if (response.isSuccess()) {
            log.info("视频上传成功!");
            log.info("VideoId: {}", videoId);
        } else {
            //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。
            // 其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            log.error("视频上传失败!");
            log.error("VideoId: {}", videoId);
            log.error("ErrorCode: {}", response.getCode());
            log.error("ErrorMessage: {}", response.getMessage());
        }

        return videoId;
    }

    /**
     * 异步删除阿里云的视频
     *
     * @param videoIds 视频ID
     */
    @Async
    public void deleteVideo(String videoIds) {
        final DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoIds);

        try {
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(ErrorInfo.DELETE_VOD_ERROR);
        }
    }

}
