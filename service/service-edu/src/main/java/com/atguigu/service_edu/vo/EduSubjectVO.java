package com.atguigu.service_edu.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ginga
 * @since 6/1/2023 下午10:14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EduSubjectVO {

    private String id;

    private String title;

    private List<EduSubjectVO> children;

}
