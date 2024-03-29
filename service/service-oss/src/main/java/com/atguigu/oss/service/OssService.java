package com.atguigu.oss.service;

import com.atguigu.common_utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * 头像上传
 *
 * @author ginga
 * @version 1.0
 * @ClassName OssService
 * @Date 12/10/2022 下午11:24
 */
public interface OssService {
    /**
     * 上传头像
     *
     * @param file 图片文件
     */
    Result uploadFileAvatar(MultipartFile file);
}
