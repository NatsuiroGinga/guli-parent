package com.atguigu.statistic.converter;

import com.atguigu.statistic.vo.StatisticVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author ginga
 * @since 20/1/2023 下午3:39
 */
@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatisticConverter {

    StatisticVo toStatisticVo(List<Long> numDataList, List<String> dateCalculatedList);

}
