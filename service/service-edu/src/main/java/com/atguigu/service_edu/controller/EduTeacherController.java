package com.atguigu.service_edu.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.pojo.EduTeacher;
import com.atguigu.service_edu.service.EduTeacherService;
import com.atguigu.service_edu.vo.param.PageParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.EndDocument;
import java.util.List;

/**
 * @author ginga
 * @version 1.0
 * @ClassName EduTeacherController
 * @Date 15/9/2022 下午5:13
 */
@RestController
@RequestMapping("edu-service/teacher")
@Api(tags = "讲师管理")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("all")
    public Result findAllTeacher() {
        final List<EduTeacher> teacherList = eduTeacherService.list(null);

        return Result.success(teacherList);
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacher(
            @ApiParam(name = "id", value = "讲师id", required = true)
            @PathVariable
            String id
    ) {
        return eduTeacherService.removeById(id)
                ? Result.success(null)
                : Result.fail();
    }


    @ApiOperation("分页查询讲师")
    @PostMapping("page")
    public Result pageListTeacher(@RequestBody(required = false)
                                      @ApiParam("分页参数")
                                      PageParam pageParam) {
        return eduTeacherService.pageListTeacher(pageParam);
    }

    @PostMapping("add")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.save(eduTeacher)
                ? Result.success(null)
                : Result.fail();
    }

}
