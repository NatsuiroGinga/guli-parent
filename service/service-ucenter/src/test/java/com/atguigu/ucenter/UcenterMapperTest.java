package com.atguigu.ucenter;

import com.atguigu.service_pojo.pojo.UcenterMember;
import com.atguigu.ucenter.mapper.UcenterMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author ginga
 * @since 15/1/2023 上午3:52
 */
@SpringBootTest
@Slf4j
public class UcenterMapperTest {

    @Autowired
    private UcenterMapper ucenterMapper;

    @Test
    public void testList() {
        final List<UcenterMember> memberList = ucenterMapper.selectList(null);
        log.info("memberList: {}", memberList);
    }
}
