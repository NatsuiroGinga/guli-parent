package com.atguigu.common_utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author ginga
 * @since 6/1/2023 下午5:48
 */
@RequiredArgsConstructor
@Getter
public enum ErrorInfo {

    ITEM_NOT_FOUND(404, "Item not found"),
    FILE_UPLOAD_ERROR(20002, "文件上传失败"),
    FILE_UPLOAD_EMPTY(20003, "文件为空"),
    ADD_BATCH_SUBJECTS_ERROR(20004, "批量添加课程分类失败"),
    CACHE_ERROR(20005, "缓存失败"),
    CLEAR_CACHE_ERROR(20006, "清空缓存失败"),
    ADD_COURSE_ERROR(20007, "添加课程失败"),
    ADD_CHAPTER_ERROR(20008, "添加章节失败"),
    ADD_VIDEO_ERROR(20009, "添加小节失败"),
    DELETE_CHAPTER_ERROR(200010, "删除章节失败"),
    UPDATE_CHAPTER_ERROR(200011, "修改章节失败"),
    DELETE_VIDEO_ERROR(200011, "删除小节失败"),
    PUBLISH_COURSE_ERROR(200012, "发布课程失败"),
    DELETE_COURSE_ERROR(200013, "删除课程失败"),
    UPLOAD_VIDEO_ERROR(200014, "上传视频失败"),
    DELETE_VOD_ERROR(200015, "删除VOD失败"),
    PARAMS_ARE_BLANK(200016, "参数为空"),
    SEND_MSM_ERROR(200017, "发送短信失败"),
    CODE_IS_ERROR(200018, "验证码错误"),
    LOGIN_ERROR(200019, "登陆失败, 用户名或密码错误"),
    REGISTER_ERROR(200020, "注册失败"),
    NOT_LOGIN(200021, "用户未登录"),
    MEMBER_ALREADY_EXISTS(200022, "注册失败, 用户已存在"),
    GET_VIDEO_AUTH_ERROR(200023, "获取视频凭证失败"),
    MEMBER_NOT_EXISTS(200024, "用户不存在"),
    ADD_ORDER_ERROR(200025, "添加订单失败"),
    COURSE_NOT_EXISTS(200026, "课程不存在"),
    TEACHER_NOT_EXISTS(200027, "教师不存在"),
    ORDER_NOT_EXISTS(200028, "订单不存在"),
    ORDER_ALREADY_PAID(200029, "订单已支付"),
    TOKEN_IS_INVALID(200030, "token非法"),
    PARAMS_ARE_INVALID(200031, "参数非法"),
    STATISTIC_ALREADY_EXISTS(200032, "该日期已被统计过"),
    STATISTIC_FAILED(200033, "统计分析失败"),
    STATISTIC_NOT_EXISTS(200034, "该日统计不存在");
    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String msg;

}
