package com.quanxiaoha.xiaohashu.search.biz.model.vo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 搜索笔记
 *
 * @Author : zpstart
 * @Date: 2025-06-16 9:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchNoteReqVO {
    @NotBlank(message = "搜索关键词不能为空")
    private String keyword;

    @Min(value = 1, message = "页码不能小于 1")
    private Integer pageNo = 1; // 默认值为第一页

    /**
     * 笔记类型：null：综合 / 0：图文 / 1：视频
     */
    @Pattern(regexp = "0|1", message = "笔记查询类型传入不正确")
    private String type;

    /**
     * 排序：null：不限 / 0：最新 / 1：最多点赞 / 2：最多评论 / 3：最多收藏
     */
    private Integer sort;

    /**
     * 发布时间范围：null：不限 / 0：一天内 / 1：一周内 / 2：半年内
     */
    private Integer publishTimeRange;
}