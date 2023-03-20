package com.atguigu.service_vod.manager;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.service_base.exceptionhandler.exception.GuliException;
import com.atguigu.service_vod.vo.VideoAuthVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.InputStream;

import static com.atguigu.common_utils.ErrorInfo.DELETE_VOD_ERROR;
import static com.atguigu.service_vod.util.VODProperties.*;

/**
 * @author ginga
 * @since 12/1/2023 下午10:25
 */
@Component
@Slf4j
public class VodManager {

    /**
     * 上传视频到阿里云
     *
     * @param title       视频标题
     * @param fileName    文件名
     * @param inputStream 文件流
     * @return 视频id
     */
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
        final DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        final DefaultAcsClient client = new DefaultAcsClient(profile);
        final DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoIds);
        log.info("开始删除视频...");

        try {
            client.getAcsResponse(request);
            log.info("删除视频成功!");
        } catch (ClientException e) {
            log.error("删除视频失败!");
            e.printStackTrace();
            throw new GuliException(DELETE_VOD_ERROR);
        }
    }

    /**
     * 获取视频凭证
     *
     * @param id 视频id
     * @return 视频播放凭证
     */
    public VideoAuthVo getAuthById(String id) {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID,
                                                           ACCESS_KEY_ID,
                                                           ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        final GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(id);
        request.setSysProtocol(ProtocolType.HTTPS);

        try {
            final GetVideoPlayAuthResponse authResponse = client.getAcsResponse(request);
            final GetVideoPlayAuthResponse.VideoMeta videoMeta = authResponse.getVideoMeta();
            final String coverURL = videoMeta.getCoverURL();
            Assert.notNull(authResponse, "response must not be null!");
            final String playAuth = authResponse.getPlayAuth();
            log.info("auth: {}", playAuth);
            final VideoAuthVo videoAuthVo = new VideoAuthVo();
            videoAuthVo.setPlayAuth(playAuth);
            videoAuthVo.setCover(coverURL);
            return videoAuthVo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(DELETE_VOD_ERROR);
        }
    }

}
