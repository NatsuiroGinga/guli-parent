package com.atguigu.statistic;

import com.atguigu.common_utils.Jackson;
import com.atguigu.service_pojo.pojo.StatisticsDaily;
import com.atguigu.statistic.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author ginga
 * @since 20/1/2023 下午6:46
 */
@SpringBootTest
@Slf4j
public class StatisticDailyServiceTest {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @Autowired
    private Jackson jackson;

    @Test
    public void testBetweenDates() {
        final List<StatisticsDaily> statisticsDailyList = statisticsDailyService.lambdaQuery()
                                                                                .select(StatisticsDaily::getCourseNum)
                                                                                .between(StatisticsDaily::getDateCalculated, "2019-01-01", "2019-02-21")
                                                                                .list();
        for (StatisticsDaily statisticsDaily : statisticsDailyList) {
            final Map<String, Object> map = jackson.bean2Map(statisticsDaily);
            log.info("statisticsDaily: {}", map.get("courseNum"));
        }
    }

    @Test
    public void testCamel() {
        final String courseNum = StringUtils.underlineToCamel("course_num");
        log.info("courseNum: {}", courseNum);
    }
}
