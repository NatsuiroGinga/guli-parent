package com.atguigu.service_edu.converter;

import com.atguigu.service_edu.vo.EduSubjectVO;
import com.atguigu.service_pojo.pojo.EduSubject;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author ginga
 * @since 6/1/2023 下午10:17
 */
@Mapper(componentModel = SPRING,
        injectionStrategy = CONSTRUCTOR)
public interface EduSubjectConverter {

    EduSubjectVO toEduSubjectVO(EduSubject eduSubject, List<EduSubject> children);

}
