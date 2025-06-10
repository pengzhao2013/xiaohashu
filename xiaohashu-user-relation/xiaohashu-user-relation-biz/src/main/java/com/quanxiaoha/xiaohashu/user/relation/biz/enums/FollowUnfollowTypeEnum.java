package com.quanxiaoha.xiaohashu.user.relation.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *  关注、取关 Type
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-10 15:42
 */
@Getter
@AllArgsConstructor
public enum FollowUnfollowTypeEnum {
    // 关注
    FOLLOW(1),
    // 取关
    UNFOLLOW(0),
    ;

    private final Integer code;
}
