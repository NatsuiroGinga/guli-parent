package com.atguigu.oss.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import com.atguigu.oss.utils.QiniuUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author ginga
 * @version 1.0
 * @ClassName OssServiceImpl
 * @Date 12/10/2022 下午11:24
 */
@Service
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
            return Result.success(ConstantPropertiesUtils.URL + filename);
        }

        return Result.fail(20001, "上传失败");
    }
}
