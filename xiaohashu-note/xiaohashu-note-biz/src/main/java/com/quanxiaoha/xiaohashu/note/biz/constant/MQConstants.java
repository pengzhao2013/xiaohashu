package com.quanxiaoha.xiaohashu.note.biz.constant;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-07 22:03
 */
public interface MQConstants {

    /**
     * Topic 主题：删除笔记本地缓存
     */
    String TOPIC_DELETE_NOTE_LOCAL_CACHE = "DeleteNoteLocalCacheTopic";

    /**
     * Topic 主题：延迟双删 Redis 笔记缓存
     */
    String TOPIC_DELAY_DELETE_NOTE_REDIS_CACHE = "DelayDeleteNoteRedisCacheTopic";
}
