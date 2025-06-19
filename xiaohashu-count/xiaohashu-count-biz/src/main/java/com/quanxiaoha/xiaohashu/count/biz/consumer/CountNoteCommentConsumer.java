package com.quanxiaoha.xiaohashu.count.biz.consumer;

import cn.hutool.core.collection.CollUtil;
import com.github.phantomthief.collection.BufferTrigger;
import com.google.common.collect.Lists;
import com.quanxiaoha.framework.common.util.JsonUtils;
import com.quanxiaoha.xiaohashu.count.biz.constant.MQConstants;
import com.quanxiaoha.xiaohashu.count.biz.domain.mapper.NoteCountDOMapper;
import com.quanxiaoha.xiaohashu.count.biz.model.dto.CountPublishCommentMqDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: zpstart
 * @Date: 2025-06-18 10:02
 * @Description: 计数: 笔记评论数
 */
@Component
@RocketMQMessageListener(consumerGroup = "xiaohashu_group_" + MQConstants.TOPIC_COUNT_NOTE_COMMENT, // Group 组
        topic = MQConstants.TOPIC_COUNT_NOTE_COMMENT // 主题 Topic
)
@Slf4j
public class CountNoteCommentConsumer implements RocketMQListener<String> {
    @Resource
    private NoteCountDOMapper noteCountDOMapper;

    private BufferTrigger<String> bufferTrigger = BufferTrigger.<String>batchBlocking()
            .bufferSize(50000) // 缓存队列的最大容量
            .batchSize(1000)   // 一批次最多聚合 1000 条
            .linger(Duration.ofSeconds(1)) // 多久聚合一次（1s 一次）
            .setConsumerEx(this::consumeMessage) // 设置消费者方法
            .build();

    @Override
    public void onMessage(String body) {
        // 往 bufferTrigger 中添加元素
        bufferTrigger.enqueue(body);
    }

    private void consumeMessage(List<String> bodys) {
        log.info("==> 【笔记评论数】聚合消息, size: {}", bodys.size());
        log.info("==> 【笔记评论数】聚合消息, {}", JsonUtils.toJsonString(bodys));

        // 将聚合后的消息体 Json 转 List<CountPublishCommentMqDTO>
        List<CountPublishCommentMqDTO> countPublishCommentMqDTOList = Lists.newArrayList();
        bodys.forEach(body -> {
            try {
                List<CountPublishCommentMqDTO> list = JsonUtils.parseList(body, CountPublishCommentMqDTO.class);
                countPublishCommentMqDTOList.addAll(list);
            } catch (Exception e) {
                log.error("", e);
            }
        });

        // 按笔记 ID 进行分组
        Map<Long, List<CountPublishCommentMqDTO>> groupMap = countPublishCommentMqDTOList.stream()
                .collect(Collectors.groupingBy(CountPublishCommentMqDTO::getNoteId));

        // 循环分组字典
        for (Map.Entry<Long, List<CountPublishCommentMqDTO>> entry : groupMap.entrySet()) {
            // 笔记 ID
            Long noteId = entry.getKey();
            // 评论数
            int count = CollUtil.size(entry.getValue());

            // 若评论数大于零，则执行更新操作：累加评论总数
            if (count > 0) {
                noteCountDOMapper.insertOrUpdateCommentTotalByNoteId(count, noteId);
            }
        }
    }
}
