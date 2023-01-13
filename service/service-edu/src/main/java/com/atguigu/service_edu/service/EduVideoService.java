package com.atguigu.service_edu.service;

import com.atguigu.common_utils.Result;
import com.atguigu.service_edu.pojo.EduVideo;
import com.atguigu.service_edu.vo.param.VideoParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 17400
 * @description 针对表【edu_video(课程视频)】的数据库操作Service
 * @createDate 2023-01-07 13:59:11
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 添加小节
     *
     * @param videoParam 小节参数
     */
    Result add(VideoParam videoParam);

    /**
     * 根据小节id删除小节
     *
     * @param id 小节id
     */
    Result removeVideoById(String id);
}
