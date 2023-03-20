package com.atguigu.statistic.service;

import com.atguigu.common_utils.Result;
import com.atguigu.service_pojo.pojo.StatisticsDaily;
import com.atguigu.statistic.vo.param.QueryStatisticsParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author ginga
 * @since 20/1/2023 下午1:10
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    /**
     * 每天凌晨01:30:00统计前一天的数据并保存到数据库 <br>
     */
    void statistic();

    /**
     * 获取实时数据统计
     *
     * @return redis中的注册人数, 登录人数, 播放视频数, 新增课程数
     */
//    Result getRealtimeStatistics();

    /**
     * 获取数据统计
     *
     * @param queryStatisticsParam 查询参数
     * @return 统计结果
     */
    Result queryStatistics(QueryStatisticsParam queryStatisticsParam);
}
