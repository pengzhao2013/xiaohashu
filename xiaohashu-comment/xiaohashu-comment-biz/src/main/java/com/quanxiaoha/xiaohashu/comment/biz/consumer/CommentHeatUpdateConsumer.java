package com.quanxiaoha.xiaohashu.comment.biz.consumer;

import com.github.phantomthief.collection.BufferTrigger;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.quanxiaoha.framework.common.util.JsonUtils;
import com.quanxiaoha.xiaohashu.comment.biz.constant.MQConstants;
import com.quanxiaoha.xiaohashu.comment.biz.domain.dataobject.CommentDO;
import com.quanxiaoha.xiaohashu.comment.biz.domain.mapper.CommentDOMapper;
import com.quanxiaoha.xiaohashu.comment.biz.model.bo.CommentHeatBO;
import com.quanxiaoha.xiaohashu.comment.biz.util.HeatCalculator;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * @Author: zpstart
 * @Date: 2025-06-18 11:31
 * @Description: 评论热度值计算
 */
@Component
@RocketMQMessageListener(consumerGroup = "xiaohashu_group_" + MQConstants.TOPIC_COMMENT_HEAT_UPDATE, // Group 组
        topic = MQConstants.TOPIC_COMMENT_HEAT_UPDATE // 主题 Topic
)
@Slf4j
public class CommentHeatUpdateConsumer implements RocketMQListener<String> {
    @Resource
    private CommentDOMapper commentDOMapper;

    private BufferTrigger<String> bufferTrigger = BufferTrigger.<String>batchBlocking()
            .bufferSize(50000) // 缓存队列的最大容量
            .batchSize(300)   // 一批次最多聚合 300 条
            .linger(Duration.ofSeconds(2)) // 多久聚合一次（2s 一次）
            .setConsumerEx(this::consumeMessage) // 设置消费者方法
            .build();

    @Override
    public void onMessage(String body) {
        // 往 bufferTrigger 中添加元素
        bufferTrigger.enqueue(body);
    }

    private void consumeMessage(List<String> bodys) {
        log.info("==> 【评论热度值计算】聚合消息, size: {}", bodys.size());
        log.info("==> 【评论热度值计算】聚合消息, {}", JsonUtils.toJsonString(bodys));

        // 将聚合后的消息体 Json 转 Set<Long>, 去重相同的评论 ID, 防止重复计算
        Set<Long> commentIds = Sets.newHashSet();
        bodys.forEach(body -> {
            try {
                Set<Long> list = JsonUtils.parseSet(body, Long.class);
                commentIds.addAll(list);
            } catch (Exception e) {
                log.error("", e);
            }
        });

        log.info("==> 去重后的评论 ID: {}", commentIds);

        // 批量查询评论
        List<CommentDO> commentDOS = commentDOMapper.selectByCommentIds(commentIds.stream().toList());

        // 评论 ID
        List<Long> ids = Lists.newArrayList();
        // 热度值 BO
        List<CommentHeatBO> commentBOS = Lists.newArrayList();

        // 重新计算每条评论的热度值
        commentDOS.forEach(commentDO -> {
            Long commentId = commentDO.getId();
            // 被点赞数
            Long likeTotal = commentDO.getLikeTotal();
            // 被回复数
            Long childCommentTotal = commentDO.getChildCommentTotal();

            // 计算热度值
            BigDecimal heatNum = HeatCalculator.calculateHeat(likeTotal, childCommentTotal);
            ids.add(commentId);
            commentBOS.add(CommentHeatBO.builder()
                    .id(commentId)
                    .heat(heatNum.doubleValue())
                    .build());
        });

        // 批量更新评论热度值
        commentDOMapper.batchUpdateHeatByCommentIds(ids, commentBOS);
    }
}