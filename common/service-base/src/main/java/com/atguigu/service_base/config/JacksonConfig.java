package com.atguigu.service_base.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ginga
 * @since 7/1/2023 下午3:51
 */
@Configuration
@ConditionalOnClass(JavaTimeModule.class)
public class JacksonConfig {

    /**
     * <ol>
     *    <li>日期格式化
     * <ol/>
     */
    @Bean
    @ConditionalOnProperty("spring.jackson.date-format")
    Jackson2ObjectMapperBuilderCustomizer customizeLocalDateTimeFormat(@Value("${spring.jackson.date-format}")
                                                                       String dateFormat) {
        return jacksonObjectMapperBuilder -> {
            // 1 日期格式化
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

            // 2 LocalDateTime序列化与反序列化
            jacksonObjectMapperBuilder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter))
                                      .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));

            // 3 LocalDate序列化与反序列化
            jacksonObjectMapperBuilder.serializerByType(LocalDate.class, new LocalDateSerializer(formatter))
                                      .deserializerByType(LocalDate.class, new LocalDateTimeDeserializer(formatter));
        };
    }

}
