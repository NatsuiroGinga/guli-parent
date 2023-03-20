package com.atguigu.service_edu.converter;

import com.atguigu.service_edu.vo.EduVideoVO;
import com.atguigu.service_edu.vo.param.VideoParam;
import com.atguigu.service_pojo.pojo.EduVideo;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * @author ginga
 * @since 8/1/2023 下午10:40
 */
@Mapper(componentModel = SPRING,
        injectionStrategy = CONSTRUCTOR,
        unmappedTargetPolicy = IGNORE)
public interface EduVideoConverter {

    EduVideoVO toEduVideoVO(EduVideo video);

    EduVideo toEduVideo(VideoParam videoParam);

}
