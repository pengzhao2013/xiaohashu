package com.quanxiaoha.xiaohashu.note.biz.consumer;

import com.quanxiaoha.xiaohashu.note.biz.constant.MQConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 删除本地笔记缓存 指定该消费者将接收类型为 String 的消息
 * RocketMQListener 接口中必须实现的方法，当接收到消息时，此方法会被调用
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-07 22:06
 */
@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "xiaohashu_group", // Group
        topic = MQConstants.TOPIC_DELETE_NOTE_LOCAL_CACHE, // 消费的主题 Topic
        messageModel = MessageModel.BROADCASTING) // 消息模式为广播模式，在这种模式下，所有的消费者都会接收到所有发布到主题的消息
public class DeleteNoteLocalCacheConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String body) {
        Long noteId = Long.valueOf(body);
        log.info("## 消费者消费成功, noteId: {}", noteId);
    }
}
