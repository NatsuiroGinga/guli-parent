package com.atguigu.statistic.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ginga
 * @since 20/1/2023 下午3:38
 */
@Data
public class StatisticVo {
    private List<String> dateCalculatedList;
    private List<Long> numDataList;
}
