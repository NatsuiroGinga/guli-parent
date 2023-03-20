package com.atguigu.front.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.front.converter.BannerConverter;
import com.atguigu.front.mapper.CrmBannerMapper;
import com.atguigu.front.service.CrmBannerFrontService;
import com.atguigu.front.vo.BannerVo;
import com.atguigu.service_base.aop.cache.Cache;
import com.atguigu.service_pojo.pojo.CrmBanner;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.concurrent.TimeUnit.DAYS;

/**
 * @author 17400
 * @description 针对表【crm_banner(首页banner表)】的数据库操作Service实现
 * @createDate 2023-01-14 14:45:17
 */
@Service
@RequiredArgsConstructor
public class CrmBannerFrontServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner>
        implements CrmBannerFrontService {

    private final BannerConverter bannerConverter;

    @Cache(timeout = 1, timeUnit = DAYS)
    @Override
    public Result listAll() {
        // 1 查询前两条数据
        final List<CrmBanner> bannerList = this.lambdaQuery()
                                               .orderByDesc(CrmBanner::getId)
                                               .last("limit 2")
                                               .list();
        // 2 转换为vo
        final List<BannerVo> bannerVoList = bannerConverter.toBannerVoList(bannerList);
        return Result.success(bannerVoList);
    }

}




