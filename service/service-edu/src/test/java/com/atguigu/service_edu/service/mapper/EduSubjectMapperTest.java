package com.atguigu.service_edu.service.mapper;

import com.atguigu.service_edu.mapper.EduSubjectMapper;
import com.atguigu.service_pojo.pojo.EduSubject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ginga
 * @since 6/1/2023 下午4:20
 */
@SpringBootTest
@Slf4j
public class EduSubjectMapperTest {

    @Autowired
    private EduSubjectMapper subjectMapper;

    @Test
    public void testSelectById() {
        final EduSubject eduSubject = subjectMapper.selectById("1178214681118568449");
        log.info("eduSubject = {}", eduSubject);
    }

    @Test
    public void testSelectOneSubjectByTitleAndParentId() {
        final EduSubject eduSubject = subjectMapper.selectOneSubjectByTitleAndParentId("后端开发", "0");
        log.info("eduSubject = {}", eduSubject);
    }

}
