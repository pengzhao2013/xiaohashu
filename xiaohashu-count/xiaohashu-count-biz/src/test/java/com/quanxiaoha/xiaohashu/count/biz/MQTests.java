package com.quanxiaoha.xiaohashu.count.biz;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Author: zpstart
 * @Date: 2025-06-17 15:56
 * @Description:
 */
@SpringBootTest
@Slf4j
class MQTests {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 测试：模拟发送评论发布消息
     */
    @Test
    void testBatchSendMQ() {
        for (long i = 0; i < 1620; i++) {

            // 构建消息对象
            Message<String> message = MessageBuilder.withPayload("消息体数据" + i)
                    .build();

            // 异步发送 MQ 消息
            rocketMQTemplate.asyncSend("PublishCommentTopic", message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("==> 【评论发布】MQ 发送成功，SendResult: {}", sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("==> 【评论发布】MQ 发送异常: ", throwable);
                }
            });
        }

        try {
            Thread.sleep(2000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}