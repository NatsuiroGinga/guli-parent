package com.atguigu.service_edu.service.mapper;

import com.atguigu.service_edu.dto.CoursePublishVO;
import com.atguigu.service_edu.mapper.EduCourseMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ginga
 * @since 10/1/2023 上午12:10
 */
@SpringBootTest
@Slf4j
public class EduCourseMapperTest {

    @Autowired
    private EduCourseMapper courseMapper;

    @Test
    public void testGetPublishCourseInfo() {
        final CoursePublishVO publishCourseInfo = courseMapper.getPublishCourseInfo("18");
        log.info("publishCourseInfo: {}", publishCourseInfo);
    }

}
