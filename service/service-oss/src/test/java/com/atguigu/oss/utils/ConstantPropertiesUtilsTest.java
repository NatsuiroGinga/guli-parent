package com.atguigu.oss.utils;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ginga
 * @version 1.0
 * @ClassName ConstantPropertiesUtilsTest
 * @Date 12/10/2022 下午11:09
 */
@SpringBootTest
public class ConstantPropertiesUtilsTest {

    @Test
    public void testAfterPropertiesSet() {
        System.out.println(ConstantPropertiesUtils.ACCESS_KEY);
        System.out.println(ConstantPropertiesUtils.SECRET_KEY);
    }

}
