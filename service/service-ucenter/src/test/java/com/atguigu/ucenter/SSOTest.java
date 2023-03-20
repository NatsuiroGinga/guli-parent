package com.atguigu.ucenter;

import com.atguigu.common_utils.sso.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author ginga
 * @since 16/1/2023 上午12:28
 */
@Slf4j
public class SSOTest {

    private static final String password = "123456";

    @Test
    public void testMDD5() {
        final String encrypt = MD5Utils.encrypt(password);
        log.info("加密后的密码: {}", encrypt);
    }

}
