package com.quanxiaoha.xiaohashu.user.relation.biz.model.vo.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询关注列表
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-10 9:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindFollowingUserRspVO {
    private Long userId;

    private String avatar;

    private String nickname;

    private String introduction;
}