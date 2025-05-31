package com.quanxiaoha.framework.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zpstart
 * @create 2025-05-31 21:48
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
