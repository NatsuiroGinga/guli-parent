package com.atguigu.service_edu.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.service.EduCourseService;
import com.atguigu.service_edu.vo.param.CourseInfoParam;
import com.atguigu.service_edu.vo.param.CourseQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author ginga
 * @since 7/1/2023 下午2:06
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("edu-service/course")
@RequiredArgsConstructor
public class EduCourseController {

    private final EduCourseService courseService;

    @ApiOperation("添加课程")
    @PostMapping
    public Result add(@RequestBody CourseInfoParam courseInfoParam) {
        return courseService.add(courseInfoParam);
    }

    @ApiOperation("根据课程id确认信息")
    @GetMapping("publish-info/{id}")
    public Result getPublishCourseInfo(@PathVariable String id) {
        return courseService.getPublishCourseInfo(id);
    }

    @ApiOperation("最终发布课程")
    @PutMapping("publish/{id}")
    public Result publish(@PathVariable String id) {
        return courseService.publish(id);
    }

    @ApiOperation("获取课程列表")
    @PostMapping("list/{current}/{limit}")
    public Result listPages(@ApiParam(value = "当前页", defaultValue = "1") @PathVariable Long current,
                            @ApiParam(value = "一页条数", defaultValue = "10") @PathVariable Long limit,
                            @RequestBody(required = false) @ApiParam("查询参数") CourseQueryParam courseQueryParam) {
        return courseService.listPages(current, limit, courseQueryParam);
    }


    @ApiOperation("删除课程")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        return courseService.delete(id);
    }

}
