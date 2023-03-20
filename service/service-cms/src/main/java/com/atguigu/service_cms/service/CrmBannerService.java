package com.atguigu.service_cms.service;

import com.atguigu.common_utils.Result;
import com.atguigu.service_cms.vo.param.BannerPageParam;
import com.atguigu.service_pojo.pojo.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 17400
 * @description 针对表【crm_banner(首页banner表)】的数据库操作Service
 * @createDate 2023-01-14 14:45:17
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 分页查询
     *
     * @param pageParam 分页参数
     */
    Result listPages(BannerPageParam pageParam);

}
