package com.quanxiaoha.xiaohashu.auth.constant;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-03 10:13
 */
public class RedisKeyConstants {
    /**
     * 验证码 KEY 前缀
     */
    private static final String VERIFICATION_CODE_KEY_PREFIX = "verification_code:";

    /**
     * 用户角色数据 KEY 前缀
     */
    private static final String USER_ROLES_KEY_PREFIX = "user:roles:";

    /**
     * 小哈书全局 ID 生成器 KEY
     */
    public static final String XIAOHASHU_ID_GENERATOR_KEY = "xiaohashu_id_generator";

    /**
     * 角色对应的权限集合 KEY 前缀
     */
    private static final String ROLE_PERMISSIONS_KEY_PREFIX = "role:permissions:";

    /**
     * 构建验证码 KEY
     * @param phone
     * @return
     */
    public static String buildVerificationCodeKey(String phone) {
        return VERIFICATION_CODE_KEY_PREFIX + phone;
    }

    /**
     * 用户对应的角色集合 KEY
     * @param userId
     * @return
     */
    public static String buildUserRoleKey(Long userId) {
        return USER_ROLES_KEY_PREFIX + userId;
    }

    /**
     * 构建角色对应的权限集合 KEY
     * @param roleKey
     * @return
     */
    public static String buildRolePermissionsKey(String roleKey) {
        return ROLE_PERMISSIONS_KEY_PREFIX + roleKey;
    }
}
