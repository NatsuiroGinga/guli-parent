package com.atguigu.service_edu.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.service.EduChapterService;
import com.atguigu.service_edu.vo.param.ChapterParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import static com.atguigu.common_utils.ErrorInfo.DELETE_CHAPTER_ERROR;

/**
 * @author ginga
 * @since 8/1/2023 下午10:22
 */
@Api(tags = "章节管理")
@RestController
@RequestMapping("edu-service/chapter")
@RequiredArgsConstructor
public class EduChapterController {

    private final EduChapterService chapterService;

    @ApiOperation("查询课程大纲")
    @GetMapping("course-outline/{courseId}")
    public Result listCourseOutline(@PathVariable String courseId) {
        return chapterService.listCourseOutline(courseId);
    }

    @ApiOperation("添加章节")
    @PostMapping
    public Result add(@RequestBody ChapterParam chapterParam) {
        return chapterService.add(chapterParam);
    }

    @ApiOperation("删除章节")
    @DeleteMapping("{id}")
    public Result delete(@PathVariable String id) {
        return chapterService.removeById(id) ? Result.success() : Result.fail(DELETE_CHAPTER_ERROR);
    }

    @ApiOperation("修改章节")
    @PutMapping()
    public Result update(@RequestBody ChapterParam chapterParam) {
        return chapterService.updateById(chapterParam);
    }

}
