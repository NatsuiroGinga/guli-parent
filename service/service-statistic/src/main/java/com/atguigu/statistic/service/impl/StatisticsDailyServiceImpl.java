package com.atguigu.statistic.service.impl;

import com.atguigu.common_utils.ErrorInfo;
import com.atguigu.common_utils.Jackson;
import com.atguigu.common_utils.Result;
import com.atguigu.common_utils.cache.CacheUtils;
import com.atguigu.service_base.exceptionhandler.exception.GuliException;
import com.atguigu.service_pojo.pojo.StatisticsDaily;
import com.atguigu.statistic.converter.StatisticConverter;
import com.atguigu.statistic.mapper.StatisticsDailyMapper;
import com.atguigu.statistic.service.StatisticsDailyService;
import com.atguigu.statistic.vo.StatisticVo;
import com.atguigu.statistic.vo.param.QueryStatisticsParam;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import static com.atguigu.statistic.util.StatisticProperties.DATETIME_FORMATTER;

/**
 * @author ginga
 * @since 20/1/2023 下午1:11
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily>
        implements StatisticsDailyService {

    private final StringRedisTemplate redisTemplate;

    private final StatisticConverter statisticConverter;

    private final Jackson jackson;

    @Scheduled(cron = "0 30 01 ? * *")
    public void statistic() {
        final LocalDate today = LocalDate.now();
        final LocalDate yesterday = today.minusDays(1);
        // 1 取昨天数据
        final StatisticsDaily statistics = getStatisticsDailyFromRedis(yesterday.format(DATETIME_FORMATTER));

        try { // 2 保存
            this.save(statistics);
        } catch (Exception e) {
            log.error("统计数据失败: {}", e.getLocalizedMessage());
            throw new GuliException(ErrorInfo.STATISTIC_FAILED);
        }
    }

    /*@Override
    public Result getRealtimeStatistics() {
        final StatisticsDaily statisticsDaily = getStatisticsDailyFromRedis(null);
        final StatisticVo statisticVo = statisticConverter.toStatisticVo(statisticsDaily);
        return Result.success(statisticVo);
    }*/

    @Override
    public Result queryStatistics(QueryStatisticsParam queryStatisticsParam) {
        Assert.notNull(queryStatisticsParam, "查询参数不能为空");
        final String type = queryStatisticsParam.getType();
        final String begin = queryStatisticsParam.getBegin();
        final String end = queryStatisticsParam.getEnd();
        final String camel = com.baomidou.mybatisplus.core.toolkit.StringUtils.underlineToCamel(type);

        // 1 校验参数
        if (StringUtils.isBlank(type) || StringUtils.isBlank(begin) || StringUtils.isBlank(end)) {
            return Result.fail(ErrorInfo.PARAMS_ARE_BLANK);
        }
        // 2 校验日期格式正确
        LocalDate endDate;
        try {
            final LocalDate beginDate = DATETIME_FORMATTER.parse(begin, LocalDate::from);
            endDate = DATETIME_FORMATTER.parse(end, LocalDate::from);
            // 2.1 校验日期范围
            if (!endDate.isAfter(beginDate) || endDate.isAfter(LocalDate.now())) {
                return Result.fail(ErrorInfo.PARAMS_ARE_INVALID);
            }
        } catch (DateTimeParseException e) {
            return Result.fail(ErrorInfo.PARAMS_ARE_INVALID);
        }
        // 3 查询
        final List<StatisticsDaily> statisticsDailyList = this.query()
                                                              .select(type, "date_calculated")
                                                              .between("date_calculated", begin, end)
                                                              .list();
        // 3.1 如果结束日期是今日, 则从redis中取数据
        if (endDate.isEqual(LocalDate.now())) {
            final StatisticsDaily statisticsDaily = getStatisticsDailyFromRedis(null);
            statisticsDailyList.add(statisticsDaily);
        }
        // 4 转换
        List<String> dateCalculatedList = statisticsDailyList.stream()
                                                             .map(StatisticsDaily::getDateCalculated)
                                                             .collect(Collectors.toList());
        List<Long> numDataList = statisticsDailyList.stream()
                                                    .map(statisticsDaily -> Long.valueOf(jackson.bean2Map(statisticsDaily)
                                                                                                .get(camel).toString()))
                                                    .collect(Collectors.toList());
        final StatisticVo statisticVo = statisticConverter.toStatisticVo(numDataList, dateCalculatedList);

        return Result.success(statisticVo);
    }

    /**
     * 从redis中获取数据 <br>
     * 如果date为空, 默认取今天的数据, 可以使用date参数指定日期
     *
     * @param date 日期
     * @return redis中的数据
     */
    @NotNull
    private StatisticsDaily getStatisticsDailyFromRedis(@Nullable String date) {
        final String loginCount = redisTemplate.opsForValue()
                                               .get(CacheUtils.createKeyWithoutSuffix("UcenterMemberServiceImpl", "login"));
        final String registerCount = redisTemplate.opsForValue()
                                                  .get(CacheUtils.createKeyWithoutSuffix("UcenterMemberServiceImpl",
                                                                                         "register"));
        final String videoViewCount = redisTemplate.opsForValue()
                                                   .get(CacheUtils.createKeyWithoutSuffix("VideoServiceImpl",
                                                                                          "getAuth"));
        final String addCourseCount = redisTemplate.opsForValue()
                                                   .get(CacheUtils.createKeyWithoutSuffix("EduCourseServiceImpl", "add"));

        final StatisticsDaily statistics = new StatisticsDaily();
        statistics.setDateCalculated(StringUtils.isBlank(date) ? LocalDate.now().format(DATETIME_FORMATTER) : date)
                  .setCourseNum(Long.valueOf(addCourseCount != null ? addCourseCount : "0"))
                  .setLoginNum(Long.valueOf(loginCount != null ? loginCount : "0"))
                  .setRegisterNum(Long.valueOf(registerCount != null ? registerCount : "0"))
                  .setVideoViewNum(Long.valueOf(videoViewCount != null ? videoViewCount : "0"));

        log.info("从redis中获取统计数据: {}", statistics);

        return statistics;
    }

}
