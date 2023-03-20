package com.atguigu.front.service.impl;

import com.atguigu.front.mapper.EduCourseMapper;
import com.atguigu.service_pojo.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author ginga
 * @since 14/1/2023 下午4:34
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements IService<EduCourse> {
}
