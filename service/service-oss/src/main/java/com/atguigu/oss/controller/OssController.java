package com.atguigu.oss.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 头像上传
 * @author ginga
 * @version 1.0
 * @ClassName OssController
 * @Date 12/10/2022 下午11:24
 */
@Api(tags = "上传操作")
@RestController
@RequestMapping("/edu-oss/file")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("上传头像")
    @PostMapping("upload")
    public Result uploadOss(MultipartFile file) {
        return ossService.uploadFileAvatar(file);
    }
}
