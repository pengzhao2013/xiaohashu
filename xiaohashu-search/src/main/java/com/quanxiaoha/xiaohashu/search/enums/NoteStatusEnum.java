package com.quanxiaoha.xiaohashu.search.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 笔记状态
 *
 * @Author : zpstart
 * @Date: 2025-06-16 20:58
 */
@Getter
@AllArgsConstructor
public enum NoteStatusEnum {
    BE_EXAMINE(0), // 待审核

    NORMAL(1), // 正常展示

    DELETED(2), // 被删除

    DOWNED(3), // 被下架
    ;

    private final Integer code;
}