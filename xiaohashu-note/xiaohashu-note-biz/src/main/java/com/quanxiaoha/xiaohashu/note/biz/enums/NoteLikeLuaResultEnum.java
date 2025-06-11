package com.quanxiaoha.xiaohashu.note.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 笔记点赞：执行 Lua 脚本返回结果
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-11 14:05
 */
@Getter
@AllArgsConstructor
public enum NoteLikeLuaResultEnum {
    // 布隆过滤器不存在
    NOT_EXIST(-1L),

    // 笔记已点赞
    NOTE_LIKED(1L),

    // 笔记点赞成功
    NOTE_LIKE_SUCCESS(0L),
    ;

    private final Long code;

    /**
     * 根据类型 code 获取对应的枚举
     *
     * @param code
     * @return
     */
    public static NoteLikeLuaResultEnum valueOf(Long code) {
        for (NoteLikeLuaResultEnum noteLikeLuaResultEnum : NoteLikeLuaResultEnum.values()) {
            if (Objects.equals(code, noteLikeLuaResultEnum.getCode())) {
                return noteLikeLuaResultEnum;
            }
        }
        return null;
    }
}
