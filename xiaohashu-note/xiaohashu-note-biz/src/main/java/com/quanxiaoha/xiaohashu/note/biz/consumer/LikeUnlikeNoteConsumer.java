package com.quanxiaoha.xiaohashu.note.biz.consumer;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import com.quanxiaoha.framework.common.util.JsonUtils;
import com.quanxiaoha.xiaohashu.note.biz.constant.MQConstants;
import com.quanxiaoha.xiaohashu.note.biz.domain.dataobject.NoteLikeDO;
import com.quanxiaoha.xiaohashu.note.biz.domain.mapper.NoteLikeDOMapper;
import com.quanxiaoha.xiaohashu.note.biz.model.dto.LikeUnlikeNoteMqDTO;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 笔记点赞、取消点赞 MQ 消费者
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-11 16:30
 */
@Component
@Slf4j
public class LikeUnlikeNoteConsumer {

    @Value("${rocketmq.name-server}")
    private String namesrvAddr;

    @Resource
    private NoteLikeDOMapper noteLikeDOMapper;

    private DefaultMQPushConsumer consumer;

    // 每秒创建 5000 个令牌
    @Resource
    private RateLimiter rateLimiter;

    @Bean(name = "LikeUnlikeNoteConsumer")
    public DefaultMQPushConsumer mqPushConsumer() throws MQClientException {
        // Group 组
        String group = "xiaohashu_group_" + MQConstants.TOPIC_LIKE_OR_UNLIKE;

        // 创建一个新的 DefaultMQPushConsumer 实例，并指定消费者的消费组名
        consumer = new DefaultMQPushConsumer(group);

        // 设置 RocketMQ 的 NameServer 地址
        consumer.setNamesrvAddr(namesrvAddr);

        // 订阅指定的主题，并设置主题的订阅规则（"*" 表示订阅所有标签的消息）
        consumer.subscribe(MQConstants.TOPIC_LIKE_OR_UNLIKE, "*");

        // 设置消费者消费消息的起始位置，如果队列中没有消息，则从最新的消息开始消费。
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);

        // 设置消息消费模式，这里使用集群模式 (CLUSTERING)
        consumer.setMessageModel(MessageModel.CLUSTERING);

        // 最大重试次数, 以防消息重试过多次仍然没有成功，避免消息卡在消费队列中。
        consumer.setMaxReconsumeTimes(3);
        // 设置每批次消费的最大消息数量，这里设置为 30，表示每次拉取时最多消费 30 条消息。
        consumer.setConsumeMessageBatchMaxSize(30);
        // 设置拉取间隔, 单位毫秒
        consumer.setPullInterval(1000);

        // 注册消息监听器
        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            log.info("==> 【笔记点赞、取消点赞】本批次消息大小: {}", msgs.size());
            try {
                // 令牌桶流控, 以控制数据库能够承受的 QPS
                rateLimiter.acquire();

                // 幂等性: 通过联合唯一索引保证

                // 消息体 Json 字符串转 DTO
                List<LikeUnlikeNoteMqDTO> likeUnlikeNoteMqDTOS = Lists.newArrayList();
                msgs.forEach(msg -> {
                    String msgJson = new String(msg.getBody());
                    log.info("==> Consumer - Received message: {}", msgJson);
                    likeUnlikeNoteMqDTOS.add(JsonUtils.parseObject(msgJson, LikeUnlikeNoteMqDTO.class));
                });

                // 1. 内存级操作合并
                // 按用户 ID 进行分组
                Map<Long, List<LikeUnlikeNoteMqDTO>> groupMap = likeUnlikeNoteMqDTOS.stream()
                        .collect(Collectors.groupingBy(LikeUnlikeNoteMqDTO::getUserId));

                // 对每个用户的操作按 noteId 二次分组，并过滤合并
                List<LikeUnlikeNoteMqDTO> finalOperations = groupMap.values().stream()
                        .flatMap(userOperations -> {
                            // 相同userId的List<LikeUnlikeNoteMqDTO>再按按 noteId 分组
                            Map<Long, List<LikeUnlikeNoteMqDTO>> noteGroupMap = userOperations.stream()
                                    .collect(Collectors.groupingBy(LikeUnlikeNoteMqDTO::getNoteId));

                            // 处理每个 noteId 的分组
                            return noteGroupMap.entrySet().stream()
                                    .filter(entry -> {
                                        List<LikeUnlikeNoteMqDTO> operations = entry.getValue();
                                        int size = operations.size();
                                        // 根据奇偶性判断是否需要处理
                                        if (size % 2 == 0) {
                                            // 偶数次操作：最终状态抵消，无需写入
                                            return false;
                                        } else {
                                            // 奇数次操作：保留最后一次操作
                                            return true;
                                        }
                                    })
                                    .map(entry -> {
                                        List<LikeUnlikeNoteMqDTO> ops = entry.getValue();
                                        // 取最后一次操作（消息是有序的）
                                        return ops.get(ops.size() - 1);
                                    });
                        })
                        .toList();

                // 2. 批量写入数据库
                if (CollUtil.isNotEmpty(finalOperations)) {
                    // DTO 转 DO
                    List<NoteLikeDO> noteLikeDOS = finalOperations.stream()
                            .map(finalOperation -> NoteLikeDO.builder()
                                    .userId(finalOperation.getUserId())
                                    .noteId(finalOperation.getNoteId())
                                    .createTime(finalOperation.getCreateTime())
                                    .status(finalOperation.getType())
                                    .build())
                            .toList();

                    // 批量写入
                    noteLikeDOMapper.batchInsertOrUpdate(noteLikeDOS);
                }

                // 手动 ACK，告诉 RocketMQ 这批次消息消费成功
                return ConsumeOrderlyStatus.SUCCESS;
            } catch (Exception e) {
                log.error("", e);
                // 这样 RocketMQ 会暂停当前队列的消费一段时间，再重试
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
        });

        // 启动消费者
        consumer.start();
        return consumer;
    }

    @PreDestroy
    public void destroy() {
        if (Objects.nonNull(consumer)) {
            try {
                consumer.shutdown();  // 关闭消费者
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }
}