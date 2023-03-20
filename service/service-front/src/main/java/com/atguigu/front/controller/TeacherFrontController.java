package com.atguigu.front.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.front.service.TeacherFrontService;
import com.atguigu.front.vo.param.TeacherPageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author ginga
 * @since 17/1/2023 上午12:07
 */
@Api(tags = "前台讲师管理")
@RequestMapping("edu-front/teacher")
@RestController
@RequiredArgsConstructor
public class TeacherFrontController {

    private final TeacherFrontService teacherFrontService;

    @ApiOperation("分页查询讲师")
    @PostMapping("list")
    public Result listPages(@RequestBody TeacherPageParam pageParam) {
        return teacherFrontService.listPages(pageParam);
    }

    @ApiOperation("查询讲师详情")
    @GetMapping("{id}")
    public Result profile(@PathVariable String id) {
        return teacherFrontService.profile(id);
    }

}
