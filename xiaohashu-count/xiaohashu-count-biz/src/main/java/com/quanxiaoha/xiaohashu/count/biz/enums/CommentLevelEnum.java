package com.quanxiaoha.xiaohashu.count.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: zpstart
 * @Date: 2025-06-18 10:53
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