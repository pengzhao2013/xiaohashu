package com.quanxiaoha.xiaohashu.comment.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: zpstart
 * @Date: 2025-06-20 13:30
 * @Description: 评论点赞、取消点赞 Type
 */
@Getter
@AllArgsConstructor
public enum LikeUnlikeCommentTypeEnum {
    // 点赞
    LIKE(1),

    // 取消点赞
    UNLIKE(0),
    ;

    private final Integer code;
}