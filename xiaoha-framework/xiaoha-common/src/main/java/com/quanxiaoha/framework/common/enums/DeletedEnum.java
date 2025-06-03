package com.quanxiaoha.framework.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-03 14:08
 */
@Getter
@AllArgsConstructor
public enum DeletedEnum {

    YES(true),
    NO(false);

    private final Boolean value;
}
