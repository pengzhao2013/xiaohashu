package com.quanxiaoha.xiaohashu.kv.biz.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @Author: zpstart
 * @Date: 2025-06-17 17:59
 * @Description: 评论内容
 */
@Table("comment_content")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentContentDO {

    /**
     * @PrimaryKey 用于指定表的主键。
     * 由于该表的主键包含多个字段，这里需单独创建一个复合主键类
     */
    @PrimaryKey
    private CommentContentPrimaryKey primaryKey;

    private String content;
}