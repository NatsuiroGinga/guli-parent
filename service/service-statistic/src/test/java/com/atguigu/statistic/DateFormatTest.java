package com.atguigu.statistic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author ginga
 * @since 20/1/2023 下午3:28
 */
@Slf4j
public class DateFormatTest {

    @Test
    public void testDateFormat() {
        String date = "2023-01-20";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final LocalDate localDate = formatter.parse(date, LocalDate::from);
        log.info("localDateTime: {}", localDate);
    }

}
