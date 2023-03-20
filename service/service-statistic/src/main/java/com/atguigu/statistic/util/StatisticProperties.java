package com.atguigu.statistic.util;

import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

/**
 * @author ginga
 * @since 20/1/2023 下午2:22
 */
@Getter
@Component
@PropertySource("classpath:statistic.yml")
@ConfigurationProperties("statistic")
public class StatisticProperties implements InitializingBean {

    public static DateTimeFormatter DATETIME_FORMATTER;

    @Value("${datetime-pattern}")
    private String datetimePattern;

    @Override
    public void afterPropertiesSet() {
        DATETIME_FORMATTER = DateTimeFormatter.ofPattern(datetimePattern);
    }
}
