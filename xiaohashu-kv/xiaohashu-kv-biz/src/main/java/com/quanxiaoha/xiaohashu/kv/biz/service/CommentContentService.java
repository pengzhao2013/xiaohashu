package com.quanxiaoha.xiaohashu.kv.biz.service;

import com.quanxiaoha.framework.common.response.Response;
import com.quanxiaoha.xiaohashu.kv.dto.req.BatchAddCommentContentReqDTO;

/**
 * @Author: zpstart
 * @Date: 2025-06-17 18:04
 * @Description: 评论内容存储业务
 */
public interface CommentContentService {
    /**
     * 批量添加评论内容
     * @param batchAddCommentContentReqDTO
     * @return
     */
    Response<?> batchAddCommentContent(BatchAddCommentContentReqDTO batchAddCommentContentReqDTO);
}