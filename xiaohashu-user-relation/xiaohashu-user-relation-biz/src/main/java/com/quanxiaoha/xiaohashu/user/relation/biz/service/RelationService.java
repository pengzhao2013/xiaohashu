package com.quanxiaoha.xiaohashu.user.relation.biz.service;

import com.quanxiaoha.framework.common.response.Response;
import com.quanxiaoha.xiaohashu.user.relation.biz.model.vo.FollowUserReqVO;
import com.quanxiaoha.xiaohashu.user.relation.biz.model.vo.UnfollowUserReqVO;

/**
 * 关注业务
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-08 16:13
 */
public interface RelationService {

    /**
     * 关注用户
     * @param followUserReqVO
     * @return
     */
    Response<?> follow(FollowUserReqVO followUserReqVO);

    /**
     * 取关用户
     * @param unfollowUserReqVO
     * @return
     */
    Response<?> unfollow(UnfollowUserReqVO unfollowUserReqVO);
}
