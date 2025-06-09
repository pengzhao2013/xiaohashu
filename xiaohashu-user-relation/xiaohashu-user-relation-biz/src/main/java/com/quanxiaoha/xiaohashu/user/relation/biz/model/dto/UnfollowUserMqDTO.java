package com.quanxiaoha.xiaohashu.user.relation.biz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 取关用户
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-09 17:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnfollowUserMqDTO {
    private Long userId;

    private Long unfollowUserId;

    private LocalDateTime createTime;
}