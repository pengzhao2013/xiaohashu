package com.quanxiaoha.xiaohashu.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 笔记操作
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-12 16:23
 */
@Getter
@AllArgsConstructor
public enum NoteOperateEnum {
    // 笔记发布
    PUBLISH(1),

    // 笔记删除
    DELETE(0),
    ;

    private final Integer code;
}