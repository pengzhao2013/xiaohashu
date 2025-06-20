package com.quanxiaoha.xiaohashu.comment.biz;

import com.google.common.collect.Lists;
import com.quanxiaoha.xiaohashu.comment.biz.consumer.DeleteCommentConsumer;
import com.quanxiaoha.xiaohashu.comment.biz.domain.mapper.CommentDOMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

/**
 * @Author: zpstart
 * @Date: 2025-06-20 21:10
 * @Description:
 */
@SpringBootTest
public class CommentDOMapperTest {
    @Resource
    private CommentDOMapper commentDOMapper;

    @Resource
    private DeleteCommentConsumer deleteCommentConsumer;

    @Test
    public void testCommentDOMapper() {
        List<Long> replyCommentIds = Lists.newArrayList();
        deleteCommentConsumer.recurrentGetReplyCommentId(
                replyCommentIds, Collections.singletonList(14002L));
        System.out.println(replyCommentIds);
    }
}