package com.quanxiaoha.xiaohashu.user.relation.biz.service;

import com.quanxiaoha.framework.common.response.PageResponse;
import com.quanxiaoha.framework.common.response.Response;
import com.quanxiaoha.xiaohashu.user.relation.biz.model.vo.req.FindFansListReqVO;
import com.quanxiaoha.xiaohashu.user.relation.biz.model.vo.req.FindFollowingListReqVO;
import com.quanxiaoha.xiaohashu.user.relation.biz.model.vo.req.FollowUserReqVO;
import com.quanxiaoha.xiaohashu.user.relation.biz.model.vo.req.UnfollowUserReqVO;
import com.quanxiaoha.xiaohashu.user.relation.biz.model.vo.resp.FindFansUserRspVO;
import com.quanxiaoha.xiaohashu.user.relation.biz.model.vo.resp.FindFollowingUserRspVO;

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

    /**
     * 查询关注列表
     * @param findFollowingListReqVO
     * @return
     */
    PageResponse<FindFollowingUserRspVO> findFollowingList(FindFollowingListReqVO findFollowingListReqVO);

    /**
     * 查询粉丝列表
     * @param findFansListReqVO
     * @return
     */
    PageResponse<FindFansUserRspVO> findFansList(FindFansListReqVO findFansListReqVO);
}
