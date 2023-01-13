package com.atguigu.service_edu.controller;

import com.alibaba.excel.EasyExcel;
import com.atguigu.common_utils.Jackson;
import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.listener.ExcelSubjectListener;
import com.atguigu.service_edu.service.EduSubjectService;
import com.atguigu.service_edu.vo.param.ExcelSubjectParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.atguigu.common_utils.ErrorInfo.ADD_BATCH_SUBJECTS_ERROR;

/**
 * @author ginga
 * @since 6/1/2023 下午4:46
 */
@Api(tags = "课程分类管理")
@RestController
@RequestMapping("edu-service/subject")
@RequiredArgsConstructor
@Slf4j
public class EduSubjectController {

    private final EduSubjectService eduSubjectService;

    private final Jackson jackson;

    @ApiOperation("Excel批量导入")
    @PostMapping("add")
    public Result addBatch(@NotNull MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),
                           ExcelSubjectParam.class,
                           new ExcelSubjectListener(eduSubjectService, jackson))
                     .sheet()
                     .doRead();
        } catch (IOException e) {
            return Result.fail(ADD_BATCH_SUBJECTS_ERROR);
        }
        return Result.success();
    }

    @ApiOperation("查询所有课程分类")
    @GetMapping("list")
    public Result list() {
        return eduSubjectService.listAll();
    }

}
