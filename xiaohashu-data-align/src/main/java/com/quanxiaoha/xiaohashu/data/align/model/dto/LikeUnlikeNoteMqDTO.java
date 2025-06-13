package com.quanxiaoha.xiaohashu.data.align.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 点赞、取消点赞笔记
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-13 11:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeUnlikeNoteMqDTO {

    private Long userId;

    private Long noteId;

    /**
     * 0: 取消点赞， 1：点赞
     */
    private Integer type;

    /**
     * 笔记发布者 ID
     */
    private Long noteCreatorId;

    private LocalDateTime createTime;
}