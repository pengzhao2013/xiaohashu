package com.quanxiaoha.xiaohashu.search.domain.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author : zpstart
 * @Date: 2025-06-16 18:02
 */
public interface SelectMapper {
    /**
     * 根据noteId或userId查询笔记文档所需的全字段数据
     * @param noteId
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectEsNoteIndexData(@Param("noteId") Long noteId,
                                                    @Param("userId") Long userId);

    /**
     * 查询用户索引所需的全字段数据
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectEsUserIndexData(@Param("userId") Long userId);
}