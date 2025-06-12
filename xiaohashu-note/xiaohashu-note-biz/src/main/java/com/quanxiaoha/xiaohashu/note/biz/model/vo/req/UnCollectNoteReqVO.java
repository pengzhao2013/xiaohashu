package com.quanxiaoha.xiaohashu.note.biz.model.vo.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取消收藏笔记
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-12 11:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnCollectNoteReqVO {
    @NotNull(message = "笔记 ID 不能为空")
    private Long id;
}
