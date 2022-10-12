package com.atguigu.service_edu.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.pojo.EduTeacher;
import com.atguigu.service_edu.service.EduTeacherService;
import com.atguigu.service_edu.vo.param.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageListTeacher(@ApiParam(value = "当前页", defaultValue = "1") @PathVariable Long current,
                                  @ApiParam(value = "一页条数", defaultValue = "3") @PathVariable Long limit,
                                  @RequestBody(required = false) @ApiParam("分页参数") PageParam pageParam) {
        return eduTeacherService.pageListTeacher(current, limit, pageParam);
    }

    @ApiOperation("添加教师")
    @PostMapping("add")
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
    @PutMapping("update")
    public Result updateTeacher(@RequestBody EduTeacher teacher) {
        return eduTeacherService.updateById(teacher)
                ? Result.success(null)
                : Result.fail();
    }

}
