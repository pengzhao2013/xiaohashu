package com.quanxiaoha.xiaohashu.comment.biz.service;

import com.quanxiaoha.framework.common.response.PageResponse;
import com.quanxiaoha.framework.common.response.Response;
import com.quanxiaoha.xiaohashu.comment.biz.model.vo.*;

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

    /**
     * 评论列表分页查询
     * @param findCommentPageListReqVO
     * @return
     */
    PageResponse<FindCommentItemRspVO> findCommentPageList(FindCommentPageListReqVO findCommentPageListReqVO);

    /**
     * 二级评论分页查询
     * @param findChildCommentPageListReqVO
     * @return
     */
    PageResponse<FindChildCommentItemRspVO> findChildCommentPageList(FindChildCommentPageListReqVO
                                                   findChildCommentPageListReqVO);

    /**
     * 评论点赞
     * @param likeCommentReqVO
     * @return
     */
    Response<?> likeComment(LikeCommentReqVO likeCommentReqVO);

    /**
     * 取消评论点赞
     * @param unLikeCommentReqVO
     * @return
     */
    Response<?> unlikeComment(UnLikeCommentReqVO unLikeCommentReqVO);
}
