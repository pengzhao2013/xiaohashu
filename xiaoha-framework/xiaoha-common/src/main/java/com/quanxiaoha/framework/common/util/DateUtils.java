package com.quanxiaoha.framework.common.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 日期工具类
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-08 16:51
 */
public class DateUtils {

    /**
     * LocalDateTime 转时间戳 LocalDateTime本身不包含时区，转换为时间戳要指定一个时区
     *
     * @param localDateTime
     * @return
     */
    public static long localDateTime2Timestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }
//    public static long localDateTime2Timestamp2(LocalDateTime localDateTime) {
          // 将LocalDateTime转换为Instant，这里使用系统默认时区
//    如果你希望使用UTC时区，可以将ZoneId.systemDefault()替换为ZoneOffset.UTC
//    这样，无论系统默认时区是什么，获取的时间戳都是基于UTC时区的
//        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//    }

//    public static void main(String[] args) {
//        System.out.println(localDateTime2Timestamp(LocalDateTime.now()));
//        System.out.println(System.currentTimeMillis());
//        System.out.println(localDateTime2Timestamp2(LocalDateTime.now()));
//    }
}
