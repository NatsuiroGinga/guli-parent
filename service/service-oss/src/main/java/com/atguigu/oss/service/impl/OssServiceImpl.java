package com.atguigu.oss.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import com.atguigu.oss.utils.QiniuUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.atguigu.common_utils.ErrorInfo.FILE_UPLOAD_ERROR;

/**
 * @author ginga
 * @version 1.0
 * @ClassName OssServiceImpl
 * @Date 12/10/2022 下午11:24
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    @Override
    public Result uploadFileAvatar(@NotNull MultipartFile file) {
        // 原始文件名称
        final String originalFilename = file.getOriginalFilename();
        // 存储文件名
        final StringBuilder filename = new StringBuilder();
        // 文件按日期分类
        final String datePath = new DateTime().toString("yyyy/MM/dd/");
        // 唯一文件名称
        filename.append(datePath)
                .append(UUID.randomUUID())
                .append(".")
                .append(StringUtils.substringAfterLast(originalFilename, "."));
        // 上传文件
        if (QiniuUtils.upload(file, filename.toString())) {
            final String url = ConstantPropertiesUtils.URL + filename;
            log.info("头像上传成功: {}", url);
            return Result.success(url);
        }

        return Result.fail(FILE_UPLOAD_ERROR);
    }
}
