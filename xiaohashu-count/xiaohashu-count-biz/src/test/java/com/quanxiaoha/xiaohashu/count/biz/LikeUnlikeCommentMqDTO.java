package com.quanxiaoha.xiaohashu.count.biz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: zpstart
 * @Date: 2025-06-20 14:46
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeUnlikeCommentMqDTO {

    private Long userId;

    private Long commentId;

    /**
     * 0: 取消点赞， 1：点赞
     */
    private Integer type;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
