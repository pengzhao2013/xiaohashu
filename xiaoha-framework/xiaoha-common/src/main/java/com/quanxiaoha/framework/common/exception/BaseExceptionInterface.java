package com.quanxiaoha.framework.common.exception;

/**
 * @author zpstart
 * @create 2025-05-31 21:44
 */
public interface BaseExceptionInterface {
    // 获取异常码
    String getErrorCode();

    // 获取异常信息
    String getErrorMessage();
}
