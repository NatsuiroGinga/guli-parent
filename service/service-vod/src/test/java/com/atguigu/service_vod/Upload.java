package com.atguigu.service_vod;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author ginga
 * @since 12/1/2023 下午8:34
 */
public class Upload {

    //账号AK信息请填写（必选）
    private static final String accessKeyId = "LTAI5t6K96CgdMQZS4t4QJDU";
    //账号AK信息请填写（必选）
    private static final String accessKeySecret = "U8CPIkyeqRaLKHmpbgNLIAFNHGnUAc";

    public static void main(String[] args) {
        // 视频文件上传
        // 视频标题（必选）
        String title = "测试标题";
        // 1.本地文件上传和文件流上传时，文件名称为上传文件绝对路径，如：/User/sample/文件名称.mp4 （必选）
        // 2.网络流上传时，文件名称为源文件名，如文件名称.mp4（必选）。
        // 3.流式上传时，文件名称为源文件名，如文件名称.mp4（必选）。
        // 任何上传方式文件名必须包含扩展名
        String fileName = "F:\\videos\\QQ短视频20230111210932.mp4";
        // 4.流式上传，如文件流和网络流
        InputStream inputStream = null;
        // 4.1 文件流
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        testUploadStream(title, fileName, inputStream);
    }


    /**
     * 流式上传接口
     *
     * @param title
     * @param fileName
     * @param inputStream
     */
    private static void testUploadStream(String title, String fileName, InputStream inputStream) {
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName,
                                                              inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
}
