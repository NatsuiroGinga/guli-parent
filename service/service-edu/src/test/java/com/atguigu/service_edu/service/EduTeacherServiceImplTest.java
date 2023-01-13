package com.atguigu.service_edu.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author ginga
 * @version 1.0
 * @ClassName EduTeacherServiceImplTest
 * @Date 20/9/2022 下午9:01
 */
@SpringBootTest
@Slf4j
public class EduTeacherServiceImplTest {

    @Autowired
    private EduTeacherService teacherService;

    @Test
    public void testDemo() {
        final Duration duration = Duration.of(1, ChronoUnit.MINUTES);
        final long seconds = duration.getSeconds();
        log.info("seconds = {}", seconds);
    }

    @Test
    public void test02() throws IOException {
        final Runtime runtime = Runtime.getRuntime();
        runtime.exec("notepad C:\\Users\\17400\\Desktop\\a.txt");
    }

    @Test
    public void testDeleteTeacher() {
        log.info("删除教师 string: {}", teacherService.removeById("string"));
    }

}
