package com.quanxiaoha.xiaohashu.data.align.constant;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-13 11:33
 */
public class RedisKeyConstants {

    /**
     * 布隆过滤器：日增量变更数据，用户笔记点赞，取消点赞 前缀
     */
    public static final String BLOOM_TODAY_NOTE_LIKE_LIST_KEY = "bloom:dataAlign:note:likes:";

    /**
     * 布隆过滤器：日增量变更数据，用户笔记收藏，取消收藏 前缀
     */
    public static final String BLOOM_TODAY_NOTE_COLLECT_LIST_KEY = "bloom:dataAlign:note:collects:";


    /**
     * 构建完整的布隆过滤器：日增量变更数据，用户笔记点赞，取消点赞 KEY
     * @param date yyyyMMdd
     * @return
     */
    public static String buildBloomUserNoteLikeListKey(String date) {
        return BLOOM_TODAY_NOTE_LIKE_LIST_KEY + date;
    }

    /**
     * 构建完整的布隆过滤器：日增量变更数据，用户笔记收藏，取消收藏 KEY
     * @param date
     * @return
     */
    public static String buildBloomUserNoteCollectListKey(String date) {
        return BLOOM_TODAY_NOTE_COLLECT_LIST_KEY + date;
    }
}