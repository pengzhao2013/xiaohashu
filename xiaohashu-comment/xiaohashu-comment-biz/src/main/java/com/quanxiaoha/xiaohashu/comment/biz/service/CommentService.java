package com.quanxiaoha.xiaohashu.comment.biz.service;

import com.quanxiaoha.framework.common.response.Response;
import com.quanxiaoha.xiaohashu.comment.biz.model.vo.PublishCommentReqVO;

/**
 * 评论业务
 *
 * @Author : zpstart
 * @Date: 2025-06-17 13:39
 */
public interface CommentService {
    /**
     * 发布评论
     * @param publishCommentReqVO
     * @return
     */
    Response<?> publishComment(PublishCommentReqVO publishCommentReqVO);
}
