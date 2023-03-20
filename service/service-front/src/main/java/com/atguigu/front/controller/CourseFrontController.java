package com.atguigu.front.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.front.service.CourseFrontService;
import com.atguigu.front.vo.CourseDetailVo;
import com.atguigu.front.vo.param.CourseFrontParam;
import com.atguigu.service_pojo.vo.CourseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author ginga
 * @since 17/1/2023 下午1:58
 */
@Api(tags = "前台课程")
@RequestMapping("edu-front/course")
@RestController
@RequiredArgsConstructor
public class CourseFrontController {

    private final CourseFrontService courseFrontService;

    @ApiOperation("分页查询课程")
    @PostMapping("list")
    public Result list(@RequestBody CourseFrontParam courseFrontParam) {
        return courseFrontService.list(courseFrontParam);
    }

    @ApiOperation("查询课程详情")
    @GetMapping("{id}")
    public Result detail(@PathVariable String id) {
        return courseFrontService.detail(id);
    }

    @SneakyThrows
    @ApiOperation("根据课程id查询课程")
    @GetMapping("order/{courseId}")
    public Optional<CourseVo> order(@PathVariable String courseId) {
        final Result detail = courseFrontService.detail(courseId);
        CourseDetailVo courseDetailVo = (CourseDetailVo) detail.getData();
        return Optional.of(courseDetailVo.getCourseWebVo());
    }

    @ApiOperation("根据课程id和用户id查询课程是否购买")
    @GetMapping("status/{courseId}")
    public Result status(@RequestHeader("Authorization") String token, @PathVariable("courseId") String courseId) {
        return courseFrontService.status(courseId, token);
    }

}
