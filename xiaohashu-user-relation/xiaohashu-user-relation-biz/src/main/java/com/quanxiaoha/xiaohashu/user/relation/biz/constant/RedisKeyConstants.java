package com.quanxiaoha.xiaohashu.user.relation.biz.constant;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-08 16:50
 */
public class RedisKeyConstants {

    /**
     * 关注列表 KEY 前缀
     */
    private static final String USER_FOLLOWING_KEY_PREFIX = "following:";

    /**
     * 构建关注列表完整的 KEY
     * @param userId
     * @return
     */
    public static String buildUserFollowingKey(Long userId) {
        return USER_FOLLOWING_KEY_PREFIX + userId;
    }
}
