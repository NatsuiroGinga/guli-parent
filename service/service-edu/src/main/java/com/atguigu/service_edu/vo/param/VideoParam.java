package com.atguigu.service_edu.vo.param;

import lombok.Data;

/**
 * @author ginga
 * @since 8/1/2023 下午11:32
 */
@Data
public class VideoParam {

    private String title;

    private String chapterId;

    private String courseId;

    private String videoSourceId;

    private String videoOriginalName;

    private Integer sort;

    private Integer free;

}
