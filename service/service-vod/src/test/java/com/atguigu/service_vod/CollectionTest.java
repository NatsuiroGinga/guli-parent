package com.atguigu.service_vod;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ginga
 * @since 13/1/2023 下午4:06
 */
@SpringBootTest
@Slf4j
public class CollectionTest {

    @Test
    public void testIdList2String() {
        final String s =
                StringUtils.join(Lists.newArrayList("1", "2", "3"), ",");
        log.info(s);
    }

}
