package com.quanxiaoha.xiaohashu.user.relation.biz.constant;

/**
 * MQ 常量
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-08 18:50
 */
public interface MQConstants {
    /**
     * Topic: 关注、取关共用一个
     */
    String TOPIC_FOLLOW_OR_UNFOLLOW = "FollowUnfollowTopic";

    /**
     * 关注标签
     */
    String TAG_FOLLOW = "Follow";

    /**
     * 取关标签
     */
    String TAG_UNFOLLOW = "Unfollow";
}