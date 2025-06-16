package com.quanxiaoha.xiaohashu.search.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 笔记可见性
 *
 * @Author : zpstart
 * @Date: 2025-06-16 21:03
 */
@Getter
@AllArgsConstructor
public enum NoteVisibleEnum {
    PUBLIC(0), // 公开，所有人可见

    PRIVATE(1); // 仅自己可见

    private final Integer code;
}