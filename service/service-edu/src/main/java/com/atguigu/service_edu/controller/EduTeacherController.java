package com.atguigu.service_edu.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.service_base.aop.cache.ClearCache;
import com.atguigu.service_edu.service.EduTeacherService;
import com.atguigu.service_edu.service.impl.EduTeacherServiceImpl;
import com.atguigu.service_edu.vo.param.TeacherQueryParam;
import com.atguigu.service_pojo.pojo.EduTeacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("condition/{current}/{limit}")
    public Result pageListTeacher(@ApiParam(value = "当前页", defaultValue = "1") @PathVariable Long current,
                                  @ApiParam(value = "一页条数", defaultValue = "10") @PathVariable Long limit,
                                  @RequestBody(required = false) @ApiParam("分页参数") TeacherQueryParam teacherQueryParam) {
        return eduTeacherService.listPages(current, limit, teacherQueryParam);
    }

    @ApiOperation("添加教师")
    @PostMapping
    @ClearCache(value = "listPages", target = EduTeacherServiceImpl.class)
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        return eduTeacherService.save(eduTeacher)
                ? Result.success(null)
                : Result.fail();
    }

    @ApiOperation("根据id查询教师")
    @GetMapping("{id}")
    public Result findTeacherById(@PathVariable String id) {
        final EduTeacher teacher = eduTeacherService.getById(id);

        return teacher == null
                ? Result.fail()
                : Result.success(teacher);
    }

    @ApiOperation("修改教师信息")
    @PutMapping
    @ClearCache(value = "listPages", target = EduTeacherServiceImpl.class)
    public Result updateTeacher(@RequestBody EduTeacher teacher) {
        return eduTeacherService.updateById(teacher)
                ? Result.success(null)
                : Result.fail();
    }

}
