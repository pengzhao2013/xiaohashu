package com.quanxiaoha.xiaohashu.note.biz.model.vo.req;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 笔记仅对自己可见
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-08 9:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateNoteVisibleOnlyMeReqVO {
    @NotNull(message = "笔记 ID 不能为空")
    private Long id;
}
