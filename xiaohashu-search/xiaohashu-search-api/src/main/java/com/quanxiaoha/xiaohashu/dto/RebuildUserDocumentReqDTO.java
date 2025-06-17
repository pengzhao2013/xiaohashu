package com.quanxiaoha.xiaohashu.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 重建用户文档
 *
 * @Author : zpstart
 * @Date: 2025-06-17 10:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RebuildUserDocumentReqDTO {
    @NotNull(message = "用户 ID 不能为空")
    private Long id;
}