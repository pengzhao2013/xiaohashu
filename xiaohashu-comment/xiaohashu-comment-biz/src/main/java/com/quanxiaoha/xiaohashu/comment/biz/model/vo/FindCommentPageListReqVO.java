package com.quanxiaoha.xiaohashu.comment.biz.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zpstart
 * @Date: 2025-06-19 10:21
 * @Description: 查询评论分页数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindCommentPageListReqVO {
    @NotNull(message = "笔记 ID 不能为空")
    private Long noteId;

    @NotNull(message = "页码不能为空")
    private Integer pageNo = 1;
}