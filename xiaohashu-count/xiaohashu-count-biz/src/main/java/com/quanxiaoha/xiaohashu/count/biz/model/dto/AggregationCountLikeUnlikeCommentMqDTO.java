package com.quanxiaoha.xiaohashu.count.biz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zpstart
 * @Date: 2025-06-20 15:40
 * @Description: 聚合后计数：点赞、取消点赞评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggregationCountLikeUnlikeCommentMqDTO {
    /**
     * 评论 ID
     */
    private Long commentId;

    /**
     * 聚合后的计数
     */
    private Integer count;
}