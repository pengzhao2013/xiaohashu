package com.quanxiaoha.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-03 14:09
 */
@Getter
@AllArgsConstructor
public enum StatusEnum {
    // 启用
    ENABLE(0),
    // 禁用
    DISABLED(1);

    private final Integer value;
}

