package com.quanxiaoha.xiaohashu.user.relation.biz.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关注用户
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-08 16:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowUserReqVO {
    @NotNull(message = "被关注用户 ID 不能为空")
    private Long followUserId;
}
