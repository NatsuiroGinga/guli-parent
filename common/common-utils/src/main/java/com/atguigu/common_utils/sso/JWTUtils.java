package com.atguigu.common_utils.sso;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Optional;

/**
 * @author ginga
 * @since 14/1/2023 下午6:57
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JWTUtils {

    private static final long EXPIRE = 1000 * 60 * 60 * 24;

    private static final byte[] APP_SECRET = "#2023#01#NatsuioGinga!".getBytes();

    /**
     * 生成token字符串的方法
     *
     * @param id       用户id
     * @param nickname 用户昵称
     * @return token
     */
    public static String createToken(String id, String nickname) {
        return JWT.create()
                  .setSubject("guli-user")
                  .setIssuedAt(new Date())
                  .setExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                  .setPayload("id", id)
                  .setPayload("nickname", nickname)
                  .sign(JWTSignerUtil.hs256(APP_SECRET));
    }

    /**
     * 校验token的方法
     *
     * @param token token
     * @return 如果token有效返回true, 反之返回false
     */
    public static boolean checkToken(String token) {
        return StringUtils.isNotBlank(token) && // 如果token为空, 返回false
                JWTUtil.verify(token, APP_SECRET); // 如果token不为空, 校验token
    }

    /**
     * 解析token<br>
     * 如果token过期或者token不正确，返回null<br>
     * 如果token正确，返回token中的载荷
     *
     * @param token token
     * @return token中的载荷
     */
    @NotNull
    public static Optional<JSONObject> parse(String token) {
        // 1 检验token是否合法
        if (!checkToken(token)) {
            // 1.1 token为空或者token不正确, 返回空
            return Optional.empty();
        }

        // 2 token合法, 解析token
        final JSONObject payloads = JWT.of(token)
                                       .getPayloads();

        // 3 返回载荷
        return Optional.of(payloads);
    }

    /**
     * 根据token字符串获取用户id
     *
     * @param token token
     * @return 用户id
     */
    public static Optional<String> getMemberIdByToken(String token) {
        // 1 解析token
        return parse(token)
                .map(payloads -> payloads.getStr("id")); // 2 获取载荷
    }
}
