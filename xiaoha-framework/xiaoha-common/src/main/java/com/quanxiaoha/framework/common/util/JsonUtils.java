package com.quanxiaoha.framework.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

/**
 * @author zpstart
 * @version 1.0
 * @date 2025-06-01 22:37
 */
public class JsonUtils {
    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // JavaTimeModule 用于指定序列化和反序列化规则
//        JavaTimeModule javaTimeModule = new JavaTimeModule();

        // 支持 LocalDateTime
//        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateConstants.Y_M_D_H_M_S_FORMAT)));
//        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateConstants.Y_M_D_H_M_S_FORMAT)));

        OBJECT_MAPPER.registerModules(new JavaTimeModule()); // 解决 LocalDateTime 的序列化问题
    }

    /**
     * 初始化：统一使用 Spring Boot 个性化配置的 ObjectMapper
     *
     * @param objectMapper
     */
    public static void init(ObjectMapper objectMapper) {
        OBJECT_MAPPER = objectMapper;
    }

    /**
     *  将对象转换为 JSON 字符串 @SneakyThrows:
     *  这是 Lombok 提供的一个注解，用于简化异常处理。
     *  它会将被标注的方法中的受检异常转换为不受检异常，使得代码看起来更加简洁。
     * @param obj
     * @return
     */
    @SneakyThrows
    public static String toJsonString(Object obj) {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }
}
