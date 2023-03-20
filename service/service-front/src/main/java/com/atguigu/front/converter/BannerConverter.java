package com.atguigu.front.converter;

import com.atguigu.front.vo.BannerVo;
import com.atguigu.service_pojo.pojo.CrmBanner;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * @author ginga
 * @since 14/1/2023 下午3:31
 */
@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = IGNORE)
public interface BannerConverter {

    List<BannerVo> toBannerVoList(List<CrmBanner> bannerList);

}
