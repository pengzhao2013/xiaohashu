package com.quanxiaoha.xiaohashu.user.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 根据手机号查询用户信息响应
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-05 18:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserByPhoneRspDTO {

    private Long id;

    private String password;
}