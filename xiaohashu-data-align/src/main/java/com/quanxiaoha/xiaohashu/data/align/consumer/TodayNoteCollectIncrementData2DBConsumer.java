package com.quanxiaoha.xiaohashu.data.align.consumer;

import com.quanxiaoha.framework.common.util.JsonUtils;
import com.quanxiaoha.xiaohashu.data.align.constant.MQConstants;
import com.quanxiaoha.xiaohashu.data.align.constant.RedisKeyConstants;
import com.quanxiaoha.xiaohashu.data.align.constant.TableConstants;
import com.quanxiaoha.xiaohashu.data.align.domain.mapper.InsertRecordMapper;
import com.quanxiaoha.xiaohashu.data.align.model.dto.CollectUnCollectNoteMqDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Objects;

/**
 * 日增量数据落库：笔记收藏、取消收藏
 *
 * @author zpstart
 * @version 1.0
 * @date 2025-06-13 13:25
 */
@Component
@RocketMQMessageListener(consumerGroup = "xiaohashu_group_data_align_" + MQConstants.TOPIC_COUNT_NOTE_COLLECT, // Group 组
        topic = MQConstants.TOPIC_COUNT_NOTE_COLLECT // 主题 Topic
)
@Slf4j
public class TodayNoteCollectIncrementData2DBConsumer implements RocketMQListener<String> {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private InsertRecordMapper insertRecordMapper;

    /**
     * 表总分片数
     */
    @Value("${table.shards}")
    private int tableShards;

    @Override
    public void onMessage(String body) {
        log.info("## TodayNoteCollectIncrementData2DBConsumer 消费到了 MQ: {}", body);

        // 消息体 JSON 字符串转 DTO
        CollectUnCollectNoteMqDTO collectUnCollectNoteMqDTO = JsonUtils.parseObject(body, CollectUnCollectNoteMqDTO.class);

        if (Objects.isNull(collectUnCollectNoteMqDTO)) {
            return;
        }

        // 被收藏、取消收藏的笔记 ID
        Long noteId = collectUnCollectNoteMqDTO.getNoteId();
        // 笔记的发布者 ID
        Long noteCreatorId = collectUnCollectNoteMqDTO.getNoteCreatorId();

        // 今日日期
        String date = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd")); // 转字符串

        String bloomKey = RedisKeyConstants.buildBloomUserNoteCollectListKey(date);

        // 1. 布隆过滤器判断该日增量数据是否已经记录
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        // Lua 脚本路径
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("/lua/bloom_today_note_collect_check.lua")));
        // 返回值类型
        script.setResultType(Long.class);

        // 执行 Lua 脚本，拿到返回结果
        Long result = redisTemplate.execute(script, Collections.singletonList(bloomKey), noteId);

        // 若布隆过滤器判断不存在（绝对正确）
        if (Objects.equals(result, 0L)) {
            // 2. 若无，才会落库，减轻数据库压力

            // 根据分片总数，取模，分别获取对应的分片序号
            long userIdHashKey = noteCreatorId % tableShards;
            long noteIdHashKey = noteId % tableShards;

            // 编程式事务，保证多语句的原子性
            transactionTemplate.execute(status -> {
                try {
                    // 将日增量变更数据，分别写入两张表
                    // - t_data_align_note_collect_count_temp_日期_分片序号
                    // - t_data_align_user_collect_count_temp_日期_分片序号
                    insertRecordMapper.insert2DataAlignNoteCollectCountTempTable(TableConstants.buildTableNameSuffix(date, noteIdHashKey), noteId);
                    insertRecordMapper.insert2DataAlignUserCollectCountTempTable(TableConstants.buildTableNameSuffix(date, userIdHashKey), noteCreatorId);

                    return true;
                } catch (Exception ex) {
                    status.setRollbackOnly(); // 标记事务为回滚
                    log.error("", ex);
                }
                return false;
            });


            // 4. 数据库写入成功后，再添加布隆过滤器中
            RedisScript<Long> bloomAddScript = RedisScript.of("return redis.call('BF.ADD', KEYS[1], ARGV[1])", Long.class);
            redisTemplate.execute(bloomAddScript, Collections.singletonList(bloomKey), noteId);
        }
    }
}
