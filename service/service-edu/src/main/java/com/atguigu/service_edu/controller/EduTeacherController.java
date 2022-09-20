package com.atguigu.service_edu.controller;

import com.atguigu.service_edu.pojo.EduTeacher;
import com.atguigu.service_edu.service.EduTeacherService;
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
    public List<EduTeacher> findAllTeacher() {
        return eduTeacherService.list(null);
    }

    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("{id}")
    public boolean removeTeacher(
            @ApiParam(name = "id", value = "讲师id", required = true)
            @PathVariable()
            String id
    ) {
        return eduTeacherService.removeById(id);
    }

}
