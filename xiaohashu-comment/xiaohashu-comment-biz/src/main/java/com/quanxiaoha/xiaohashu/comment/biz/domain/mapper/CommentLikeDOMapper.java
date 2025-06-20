package com.quanxiaoha.xiaohashu.comment.biz.domain.mapper;

import com.quanxiaoha.xiaohashu.comment.biz.domain.dataobject.CommentLikeDO;
import com.quanxiaoha.xiaohashu.comment.biz.model.dto.LikeUnlikeCommentMqDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentLikeDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommentLikeDO record);

    int insertSelective(CommentLikeDO record);

    CommentLikeDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentLikeDO record);

    int updateByPrimaryKey(CommentLikeDO record);

    /**
     * 查询某个评论是否被点赞
     *
     * @param userId
     * @param commentId
     * @return
     */
    int selectCountByUserIdAndCommentId(@Param("userId") Long userId,
                                        @Param("commentId") Long commentId);

    /**
     * 查询对应用户点赞的所有评论
     * @param userId
     * @return
     */
    List<CommentLikeDO> selectByUserId(@Param("userId") Long userId);

    /**
     * 批量删除点赞记录
     * @param unlikes
     * @return
     */
    int batchDelete(@Param("unlikes") List<LikeUnlikeCommentMqDTO> unlikes);

    /**
     * 批量添加点赞记录
     * # 保证当插入重复点赞记录时，不会抛出异常，从而导致批次消息一直被重复消费
     * @param likes
     * @return
     */
    int batchInsert(@Param("likes") List<LikeUnlikeCommentMqDTO> likes);
}