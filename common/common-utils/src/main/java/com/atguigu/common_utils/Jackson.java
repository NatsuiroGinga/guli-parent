package com.atguigu.common_utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author ginga
 * @since 6/1/2023 下午5:38
 */
@Component
@RequiredArgsConstructor
public class Jackson {

    private final ObjectMapper objectMapper;

    /**
     * 将对象转换为json字符串
     *
     * @param value 要转换的对象
     * @return json字符串
     */
    public String bean2Json(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json字符串转换为对象
     *
     * @param content   json字符串
     * @param valueType 要转换的对象类型
     * @param <T>       要转换的对象类型
     * @return 转换后的对象
     */
    public <T> T json2Bean(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json字符串转换为对象
     *
     * @param content      json字符串
     * @param valueTypeRef 要转换的对象类型
     * @param <T>          要转换的对象类型
     * @return 转换后的对象
     */
    public <T> T json2Bean(String content, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
