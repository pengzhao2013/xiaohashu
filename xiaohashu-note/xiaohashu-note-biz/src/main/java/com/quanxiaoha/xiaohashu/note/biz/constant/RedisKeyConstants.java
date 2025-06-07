package com.quanxiaoha.xiaohashu.note.biz.constant;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-07 16:35
 */
public class RedisKeyConstants {
    /**
     * 笔记详情 KEY 前缀
     */
    public static final String NOTE_DETAIL_KEY = "note:detail:";


    /**
     * 构建完整的笔记详情 KEY
     * @param noteId
     * @return
     */
    public static String buildNoteDetailKey(Long noteId) {
        return NOTE_DETAIL_KEY + noteId;
    }
}