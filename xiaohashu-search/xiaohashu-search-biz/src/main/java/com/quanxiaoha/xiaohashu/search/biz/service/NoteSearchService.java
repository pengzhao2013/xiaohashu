package com.quanxiaoha.xiaohashu.search.biz.service;

import com.quanxiaoha.framework.common.response.PageResponse;
import com.quanxiaoha.framework.common.response.Response;
import com.quanxiaoha.xiaohashu.dto.RebuildNoteDocumentReqDTO;
import com.quanxiaoha.xiaohashu.search.biz.model.vo.SearchNoteReqVO;
import com.quanxiaoha.xiaohashu.search.biz.model.vo.SearchNoteRspVO;

/**
 * 笔记搜索业务
 *
 * @Author : zpstart
 * @Date: 2025-06-16 9:50
 */
public interface NoteSearchService {
    /**
     * 搜索笔记
     * @param searchNoteReqVO
     * @return
     */
    PageResponse<SearchNoteRspVO> searchNote(SearchNoteReqVO searchNoteReqVO);

    /**
     * 重建笔记文档
     * @param rebuildNoteDocumentReqDTO
     * @return
     */
    Response<Long> rebuildDocument(RebuildNoteDocumentReqDTO rebuildNoteDocumentReqDTO);
}