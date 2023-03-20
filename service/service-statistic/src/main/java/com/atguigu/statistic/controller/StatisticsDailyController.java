package com.atguigu.statistic.controller;

import com.atguigu.common_utils.Result;
import com.atguigu.statistic.service.StatisticsDailyService;
import com.atguigu.statistic.vo.param.QueryStatisticsParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginga
 * @since 20/1/2023 下午1:09
 */
@Api(tags = "统计分析")
@RequestMapping("/edu-statistic/daily")
@RestController
@RequiredArgsConstructor
public class StatisticsDailyController {

    private final StatisticsDailyService statisticsDailyService;

    /*@ApiOperation("获取实时数据统计")
    @GetMapping
    public Result statistics() {
        return statisticsDailyService.getRealtimeStatistics();
    }*/

    @ApiOperation("获取数据统计")
    @PostMapping("search")
    public Result search(@RequestBody QueryStatisticsParam queryStatisticsParam) {
        return statisticsDailyService.queryStatistics(queryStatisticsParam);
    }

}
