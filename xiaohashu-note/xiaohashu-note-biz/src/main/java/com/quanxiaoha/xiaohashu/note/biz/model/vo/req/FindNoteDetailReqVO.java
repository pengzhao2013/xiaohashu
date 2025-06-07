package com.quanxiaoha.xiaohashu.note.biz.model.vo.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询笔记详情
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-07 15:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindNoteDetailReqVO {
    @NotNull(message = "笔记 ID 不能为空")
    private Long id;
}