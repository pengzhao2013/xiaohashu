package com.quanxiaoha.xiaohashu.search.service;

import com.quanxiaoha.framework.common.response.PageResponse;
import com.quanxiaoha.xiaohashu.search.model.vo.SearchUserReqVO;
import com.quanxiaoha.xiaohashu.search.model.vo.SearchUserRspVO;

/**
 * 用户搜索业务
 *
 * @Author : zpstart
 * @Date: 2025-06-15 20:23
 */
public interface UserSearchService {
    /**
     * 搜索用户
     * @param searchUserReqVO
     * @return
     */
    PageResponse<SearchUserRspVO> searchUser(SearchUserReqVO searchUserReqVO);
}