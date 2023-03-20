package com.atguigu.order;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author ginga
 * @since 19/1/2023 下午9:32
 */
@Slf4j
public class IdUtilTest {

    @Test
    public void testNextId() {
        final String nextIdStr = IdUtil.getSnowflakeNextIdStr();
        log.info("nextId: {}", nextIdStr);
    }

}
