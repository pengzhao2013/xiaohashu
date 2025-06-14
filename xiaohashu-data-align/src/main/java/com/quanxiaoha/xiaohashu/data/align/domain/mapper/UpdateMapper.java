package com.quanxiaoha.xiaohashu.data.align.domain.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 更新用户计数表
 *
 * @Author : zpstart
 * @Date: 2025-06-14 16:25
 */
public interface UpdateMapper {
    /**
     * 更新 t_user_count 计数表总关注数
     * @param userId
     * @return
     */
    int updateUserFollowingTotalByUserId(@Param("userId") long userId,
                                         @Param("followingTotal") int followingTotal);

    /**
     * 更新 t_user_count 计数表总粉丝数
     * @param userId
     * @return
     */
    int updateUserFansTotalByUserId(@Param("userId") long userId,
                                         @Param("fansTotal") int fansTotal);

    /**
     * 更新 t_note_count 计数表笔记点赞数
     */
    int updateNoteLikeTotalByUserId(@Param("noteId") long noteId,
                                    @Param("noteLikeTotal") int noteLikeTotal);

    /**
     * 更新 t_note_count 计数表笔记收藏数
     */
    int updateNoteCollectTotalByUserId(@Param("noteId") long noteId,
                                    @Param("noteCollectTotal") int noteCollectTotal);

    /**
     * 更新 t_user_count 计数表发布笔记数
     */
    int updateUserNoteTotalByUserId(@Param("userId") long userId,
                                    @Param("noteTotal") int noteTotal);

    /**
     * 更新 t_user_count 计数表获得的总收藏数
     */
    int updateUserCollectTotalByUserId(@Param("userId") long userId,
                                       @Param("collectTotal") int collectTotal);

    /**
     * 更新 t_user_count 计数表获得的总点赞数
     */
    int updateUserLikeTotalByUserId(@Param("userId") long userId,
                                    @Param("likeTotal") int likeTotal);
}