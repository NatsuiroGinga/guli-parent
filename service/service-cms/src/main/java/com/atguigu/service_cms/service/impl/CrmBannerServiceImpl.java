package com.atguigu.service_cms.service.impl;

import com.atguigu.common_utils.Result;
import com.atguigu.service_cms.mapper.CrmBannerMapper;
import com.atguigu.service_cms.service.CrmBannerService;
import com.atguigu.service_cms.vo.param.BannerPageParam;
import com.atguigu.service_pojo.pojo.CrmBanner;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * @author 17400
 * @description 针对表【crm_banner(首页banner表)】的数据库操作Service实现
 * @createDate 2023-01-14 14:45:17
 */
@Service
@RequiredArgsConstructor
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner>
        implements CrmBannerService {

    @Override
    public Result listPages(@NotNull BannerPageParam pageParam) {
        final long current = pageParam.getCurrent();
        final long size = pageParam.getSize();
        final Page<CrmBanner> bannerPage = new Page<>(current, size);
        final Page<CrmBanner> page = this.page(bannerPage);
        return Result.success(page);
    }

}




