package com.atguigu.service_edu;

import cn.hutool.json.JSONObject;
import com.atguigu.common_utils.sso.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author ginga
 * @since 14/1/2023 下午9:09
 */
@Slf4j
public class JWTTest {

    private final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
            "eyJzdWIiOiJndWxpLXVzZXIiLCJpYXQiOjE2NzM3MDE5MzIsImV4cCI6MTY3Mzc4ODMzMiwiaWQiOiIxMDAyIiwibmlja25hbWUiOiJnaW5nYSJ9" +
            ".bybk7wPSZt7s4j2eTLaaoS8iuMYPZkvCPdiSgYG2y7I";

    @Test
    public void testCreateToken() {
        final String token = JWTUtils.createToken("1002", "ginga");
        log.info("token: {}", token);
    }

    @Test
    public void testParse() {
        final Optional<JSONObject> jsonObject = JWTUtils.parse(token);
        log.info("jsonObject: {}", jsonObject);
    }

    @Test
    public void testGetMemberIdByToken() {
        final Optional<String> id = JWTUtils.getMemberIdByToken(token);
        log.info("id: {}", id);
    }
}
