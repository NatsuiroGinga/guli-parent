package com.atguigu.common_utils.sso;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @author ginga
 * @since 16/1/2023 上午12:43
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MD5Utils {

    private static final String SALT_1 = "!2023#Natsuio@Ginga";

    private static final String SALT_2 = "DywL@qq..com";

    /**
     * MD5加密
     *
     * @param data 数据
     * @return MD5加密后的数据
     */
    @NotNull
    public static String encrypt(String data) {
        final String md5Hex = DigestUtils.md5Hex(data + SALT_1);
        return DigestUtils.md5Hex(md5Hex + SALT_2);
    }

}
