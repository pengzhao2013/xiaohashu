package com.quanxiaoha.xiaohashu.note.biz.domain.mapper;

import com.quanxiaoha.xiaohashu.note.biz.domain.dataobject.NoteDO;

public interface NoteDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoteDO record);

    int insertSelective(NoteDO record);

    /**
     * 只查询 status 状态为 1 （正常展示中）的笔记
     * @param id 笔记id
     * @return
     */
    NoteDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NoteDO record);

    int updateByPrimaryKey(NoteDO record);

    /**
     *  仅更新笔记状态为正常展示的记录
     * @param noteDO
     * @return
     */
    int updateVisibleOnlyMe(NoteDO noteDO);

    int updateIsTop(NoteDO noteDO);
}