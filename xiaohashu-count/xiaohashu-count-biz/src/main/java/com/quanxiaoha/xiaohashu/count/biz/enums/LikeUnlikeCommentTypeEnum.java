package com.quanxiaoha.xiaohashu.count.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @Author: zpstart
 * @Date: 2025-06-20 15:41
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

    public static LikeUnlikeCommentTypeEnum valueOf(Integer code) {
        for (LikeUnlikeCommentTypeEnum likeUnlikeCommentTypeEnum : LikeUnlikeCommentTypeEnum.values()) {
            if (Objects.equals(code, likeUnlikeCommentTypeEnum.getCode())) {
                return likeUnlikeCommentTypeEnum;
            }
        }
        return null;
    }
}
