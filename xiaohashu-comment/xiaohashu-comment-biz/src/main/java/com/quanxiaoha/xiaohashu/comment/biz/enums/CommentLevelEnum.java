package com.quanxiaoha.xiaohashu.comment.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: zpstart
 * @Date: 2025-06-17 20:20
 * @Description: 评论级别
 */
@Getter
@AllArgsConstructor
public enum CommentLevelEnum {
    // 一级评论
    ONE(1),

    // 二级评论
    TWO(2),
    ;

    private final Integer code;
}