package com.quanxiaoha.xiaohashu.note.biz.service;

import com.quanxiaoha.framework.common.response.Response;
import com.quanxiaoha.xiaohashu.note.biz.model.vo.req.FindNoteDetailReqVO;
import com.quanxiaoha.xiaohashu.note.biz.model.vo.req.PublishNoteReqVO;
import com.quanxiaoha.xiaohashu.note.biz.model.vo.req.UpdateNoteReqVO;
import com.quanxiaoha.xiaohashu.note.biz.model.vo.resp.FindNoteDetailRspVO;

/**
 * 笔记业务
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-07 10:15
 */
public interface NoteService {
    /**
     * 笔记发布
     * @param publishNoteReqVO
     * @return
     */
    Response<?> publishNote(PublishNoteReqVO publishNoteReqVO);

    /**
     * 笔记详情
     * @param findNoteDetailReqVO
     * @return
     */
    Response<FindNoteDetailRspVO> findNoteDetail(FindNoteDetailReqVO findNoteDetailReqVO);

    /**
     * 笔记更新
     * @param updateNoteReqVO
     * @return
     */
    Response<?> updateNote(UpdateNoteReqVO updateNoteReqVO);
}
