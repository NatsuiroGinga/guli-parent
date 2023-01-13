package com.atguigu.service_edu.service.mapper;

import com.atguigu.service_edu.mapper.EduVideoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ginga
 * @since 10/1/2023 下午9:00
 */
@SpringBootTest
@Slf4j
public class EduVideoMapperTest {

    @Autowired
    private EduVideoMapper videoMapper;

    @Transactional
    @Test
    public void testDeleteByCourseId() {
        final boolean delete = videoMapper.deleteByCourseId("1612488514144899074");
        log.info("delete: {}", delete);
    }

}
