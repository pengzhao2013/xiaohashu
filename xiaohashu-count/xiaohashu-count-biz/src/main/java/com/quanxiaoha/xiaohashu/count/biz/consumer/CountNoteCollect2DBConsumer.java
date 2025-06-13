package com.quanxiaoha.xiaohashu.count.biz.consumer;

import cn.hutool.core.collection.CollUtil;
import com.google.common.util.concurrent.RateLimiter;
import com.quanxiaoha.framework.common.util.JsonUtils;
import com.quanxiaoha.xiaohashu.count.biz.constant.MQConstants;
import com.quanxiaoha.xiaohashu.count.biz.domain.mapper.NoteCountDOMapper;
import com.quanxiaoha.xiaohashu.count.biz.domain.mapper.UserCountDOMapper;
import com.quanxiaoha.xiaohashu.count.biz.model.dto.AggregationCountCollectUnCollectNoteMqDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * 计数: 笔记收藏数落库
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-12 15:03
 */
@Component
@RocketMQMessageListener(consumerGroup = "xiaohashu_group_" + MQConstants.TOPIC_COUNT_NOTE_COLLECT_2_DB, // Group 组
        topic = MQConstants.TOPIC_COUNT_NOTE_COLLECT_2_DB // 主题 Topic
)
@Slf4j
public class CountNoteCollect2DBConsumer implements RocketMQListener<String> {

    @Resource
    private NoteCountDOMapper noteCountDOMapper;

    @Resource
    private UserCountDOMapper userCountDOMapper;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private RateLimiter rateLimiter;

    @Override
    public void onMessage(String body) {
        // 流量削峰：通过获取令牌，如果没有令牌可用，将阻塞，直到获得
        rateLimiter.acquire();

        log.info("## 消费到了 MQ 【计数: 笔记收藏数入库】, {}...", body);

        List<AggregationCountCollectUnCollectNoteMqDTO> countList = null;
        try {
            countList = JsonUtils.parseList(body, AggregationCountCollectUnCollectNoteMqDTO.class);
        } catch (Exception e) {
            log.error("## 解析 JSON 字符串异常", e);
        }

        if (CollUtil.isNotEmpty(countList)) {
            // 判断数据库中 t_user_count 和 t_note_count 表，若笔记计数记录不存在，则插入；若记录已存在，则直接更新
            countList.forEach(item -> {
                Long creatorId = item.getCreatorId();
                Long noteId = item.getNoteId();
                Integer count = item.getCount();

                // 编程式事务，保证两条语句的原子性
                transactionTemplate.execute(status -> {
                    try {
                        noteCountDOMapper.insertOrUpdateCollectTotalByNoteId(count, noteId);
                        userCountDOMapper.insertOrUpdateCollectTotalByUserId(count, creatorId);
                        return true;
                    } catch (Exception ex) {
                        status.setRollbackOnly(); // 标记事务为回滚
                        log.error("", ex);
                    }
                    return false;
                });
            });
        }
    }
}