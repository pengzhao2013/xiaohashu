package com.quanxiaoha.xiaohashu.user.relation.biz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-08 18:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowUserMqDTO {
    private Long userId;

    private Long followUserId;

    private LocalDateTime createTime;
}