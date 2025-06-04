package com.quanxiaoha.xiaohashu.auth.alarm;

/**
 * @Author : zpstart
 * @Date: 2025-06-04 9:51
 */
public interface AlarmInterface {

    /**
     * 发送告警信息
     *
     * @param message
     * @return
     */
    boolean send(String message);
}
