package com.atguigu.service_edu.client;

import com.atguigu.common_utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.atguigu.common_utils.ErrorInfo.DELETE_VOD_ERROR;

/**
 * 服务降级
 *
 * @author ginga
 * @since 13/1/2023 下午5:41
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {

    @Override
    public Result delete(String id) {
        return Result.fail(DELETE_VOD_ERROR);
    }

    @Override
    public Result deleteBatch(List<String> videoIdList) {
        return Result.fail(DELETE_VOD_ERROR);
    }
}
