package com.quanxiaoha.xiaohashu.data.align.consumer;

import com.quanxiaoha.xiaohashu.data.align.constant.MQConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 日增量数据落库：笔记点赞、取消点赞
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-12 22:31
 */
@Component
@RocketMQMessageListener(consumerGroup = "xiaohashu_group_data_align_" + MQConstants.TOPIC_COUNT_NOTE_LIKE, // Group 组
        topic = MQConstants.TOPIC_COUNT_NOTE_LIKE // 主题 Topic
)
@Slf4j
public class TodayNoteLikeIncrementData2DBConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String body) {
        log.info("## TodayNoteLikeIncrementData2DBConsumer 消费到了 MQ: {}", body);

        // TODO: 1. 布隆过滤器判断该日增量数据是否已经记录

        // TODO: 2. 若无，才会落库，减轻数据库压力

        // 将日增量变更数据，分别写入两张表
        // - t_data_align_note_like_count_temp_日期_分片序号
        // - t_data_align_user_like_count_temp_日期_分片序号

        // TODO: 3. 数据库写入成功后，再添加布隆过滤器中
    }
}