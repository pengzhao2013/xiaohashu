package com.quanxiaoha.xiaohashu.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 笔记收藏：执行 Lua 脚本返回结果
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-12 9:44
 */
@Getter
@AllArgsConstructor
public enum NoteCollectLuaResultEnum {
    // 布隆过滤器或者 ZSet 不存在
    NOT_EXIST(-1L),

    // 笔记已收藏
    NOTE_COLLECTED(1L),

    // 笔记收藏成功
    NOTE_COLLECTED_SUCCESS(0L),
    ;

    private final Long code;

    /**
     * 根据类型 code 获取对应的枚举
     *
     * @param code
     * @return
     */
    public static NoteCollectLuaResultEnum valueOf(Long code) {
        for (NoteCollectLuaResultEnum noteCollectLuaResultEnum : NoteCollectLuaResultEnum.values()) {
            if (Objects.equals(code, noteCollectLuaResultEnum.getCode())) {
                return noteCollectLuaResultEnum;
            }
        }
        return null;
    }
}
