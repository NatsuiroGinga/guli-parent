package com.atguigu.front;

import com.atguigu.common_utils.Jackson;
import com.atguigu.front.mapper.EduTeacherMapper;
import com.atguigu.front.vo.TeacherPageVo;
import com.atguigu.service_pojo.pojo.EduTeacher;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ginga
 * @since 17/1/2023 上午12:17
 */
@SpringBootTest
@Slf4j
public class TeacherMapperTest {

    @Autowired
    private EduTeacherMapper teacherMapper;

    @Autowired
    private Jackson jackson;

    @SneakyThrows
    @Test
    public void testPage() {
        final Page<EduTeacher> teacherPage = new Page<>(1, 2);
        final TeacherPageVo teacherPageVo = new TeacherPageVo();
        teacherMapper.selectPage(teacherPage, null);
        BeanUtils.copyProperties(teacherPageVo, teacherPage);
        teacherPageVo.setHasNext(teacherPage.hasNext());
        teacherPageVo.setHasPrevious(teacherPage.hasPrevious());
        log.info("page: {}", teacherPageVo);
    }

}
