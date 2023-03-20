package com.atguigu.front.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ginga
 * @since 17/1/2023 下午2:52
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    private List<VideoVo> children;
}
