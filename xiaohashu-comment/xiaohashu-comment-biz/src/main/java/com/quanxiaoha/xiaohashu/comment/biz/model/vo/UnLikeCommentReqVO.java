package com.quanxiaoha.xiaohashu.comment.biz.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zpstart
 * @Date: 2025-06-20 14:56
 * @Description: 取消评论点赞
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnLikeCommentReqVO {
    @NotNull(message = "评论 ID 不能为空")
    private Long commentId;
}