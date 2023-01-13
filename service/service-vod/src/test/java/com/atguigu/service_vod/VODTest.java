package com.atguigu.service_vod;

import com.atguigu.service_vod.util.VODProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ginga
 * @since 12/1/2023 上午12:00
 */
@SpringBootTest
@Slf4j
public class VODTest {

    @Test
    public void testGetProperties() {
        log.info("accessKeyId: {}", VODProperties.ACCESS_KEY_ID);
    }

}
