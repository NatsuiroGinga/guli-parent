package com.atguigu.service_base.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author ginga
 * @since 10/1/2023 上午12:51
 */
@RequiredArgsConstructor
@Getter
public enum CourseStatus {
    /**
     * 未发布
     */
    DRAFT("Draft"),
    /**
     * 已发布
     */
    NORMAL("Normal");

    private final String status;
}
