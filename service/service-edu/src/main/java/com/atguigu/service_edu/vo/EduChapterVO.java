package com.atguigu.service_edu.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ginga
 * @since 8/1/2023 下午10:20
 */
@Data
public class EduChapterVO {

    private String id;

    private String title;

    private Integer sort;

    private List<EduVideoVO> children;
}
