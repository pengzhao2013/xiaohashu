package com.quanxiaoha.xiaohashu.user.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 根据用户 ID 查询用户信息
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-07 11:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserByIdReqDTO {
    /**
     * 手机号
     */
    @NotNull(message = "用户 ID 不能为空")
    private Long id;
}
