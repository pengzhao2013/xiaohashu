package com.quanxiaoha.xiaohashu.search.biz.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : zpstart
 * @Date: 2025-06-15 20:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchUserRspVO {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 小哈书ID
     */
    private String xiaohashuId;

    /**
     * 笔记发布总数
     */
    private Integer noteTotal;

    /**
     * 粉丝总数
     */
    private String fansTotal;

    /**
     * 昵称：关键词高亮
     */
    private String highlightNickname;
}