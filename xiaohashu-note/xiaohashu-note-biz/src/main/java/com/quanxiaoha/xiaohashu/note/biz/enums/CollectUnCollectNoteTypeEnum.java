package com.quanxiaoha.xiaohashu.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 笔记收藏、取消收藏 Type
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-12 10:49
 */
@Getter
@AllArgsConstructor
public enum CollectUnCollectNoteTypeEnum {
    // 收藏
    COLLECT(1),

    // 取消收藏
    UN_COLLECT(0),
    ;

    private final Integer code;
}