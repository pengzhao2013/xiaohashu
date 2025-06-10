package com.quanxiaoha.xiaohashu.user.relation.biz.model.vo.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取关用户
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-09 17:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnfollowUserReqVO {
    @NotNull(message = "被取关用户 ID 不能为空")
    private Long unfollowUserId;
}
