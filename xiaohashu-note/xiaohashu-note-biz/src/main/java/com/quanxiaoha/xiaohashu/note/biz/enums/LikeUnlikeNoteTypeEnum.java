package com.quanxiaoha.xiaohashu.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 笔记点赞、取消点赞 Type
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-11 16:27
 */
@Getter
@AllArgsConstructor
public enum LikeUnlikeNoteTypeEnum {
    // 点赞
    LIKE(1),

    // 取消点赞
    UNLIKE(0),
    ;

    private final Integer code;

}
