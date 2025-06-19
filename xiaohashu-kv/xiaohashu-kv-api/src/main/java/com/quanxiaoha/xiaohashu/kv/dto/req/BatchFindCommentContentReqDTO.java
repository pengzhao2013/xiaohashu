package com.quanxiaoha.xiaohashu.kv.dto.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: zpstart
 * @Date: 2025-06-18 21:53
 * @Description: 批量查询评论内容
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchFindCommentContentReqDTO {

    /**
     * 笔记 ID
     */
    @NotNull(message = "评论 ID 不能为空")
    private Long noteId;

    @NotEmpty(message = "评论内容 Key 集合")
    @Valid  // 指定集合中的 DTO 也需要进行参数校验
    private List<FindCommentContentReqDTO> commentContentKeys;
}