package com.atguigu.service_cms.vo.param;

import lombok.Data;

/**
 * @author ginga
 * @since 14/1/2023 下午3:08
 */
@Data
public class BannerPageParam {

    /**
     * 当前页
     */
    private long current;

    /**
     * 每页记录数
     */
    private long size;
}
