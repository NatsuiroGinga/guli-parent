package com.atguigu.service_edu.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @author ginga
 * @version 1.0
 * @ClassName EduTeacherServiceImplTest
 * @Date 20/9/2022 下午9:01
 */
@SpringBootTest
public class EduTeacherServiceImplTest {

    @Test
    public void testDemo() {
        String s1 = null;
        String s2 = "fd";

        System.out.println(s1 + s2);

    }

    @Test
    public void test02() throws IOException {
        final Runtime runtime = Runtime.getRuntime();
        runtime.exec("notepad C:\\Users\\17400\\Desktop\\a.txt");
    }

}
