package com.quanxiaoha.xiaohashu.comment.biz.constant;

/**
 * @Author: zpstart
 * @Date: 2025-06-17 13:52
 * @Description: MQ常量
 */
public interface MQConstants {
    /**
     * Topic: 评论发布
     */
    String TOPIC_PUBLISH_COMMENT = "PublishCommentTopic";

    /**
     * Topic: 笔记评论总数计数
     */
    String TOPIC_COUNT_NOTE_COMMENT = "CountNoteCommentTopic";

    /**
     * Topic: 评论热度值更新
     */
    String TOPIC_COMMENT_HEAT_UPDATE = "CommentHeatUpdateTopic";
}