package com.quanxiaoha.xiaohashu.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import com.quanxiaoha.framework.common.constant.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 转发请求时，将用户 ID 添加到 Header 请求头中，透传给下游服务
 *
 * @Author : zpstart
 * @Date: 2025-06-04 15:43
 */
@Component
@Slf4j
public class AddUserId2HeaderFilter implements GlobalFilter {
    /**
     *  SaReactorFilter执行优先级最高 权限校验在前面，真正执行到 AddUserId2HeaderFilter 过滤器中时，
     *  要么是接口校验通过了，要么是该接口无需校验两种情况
     * @param exchange 表示当前的 HTTP 请求和响应的上下文，
     *                 包括请求头、请求体、响应头、响应体等信息。可以通过它来获取和修改请求和响应。
     * @param chain 代表网关过滤器链，通过调用 chain.filter(exchange) 方法可以将请求传递给下一个过滤器进行处理。
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 用户 ID
        Long userId = null;
        try {
            // 获取当前登录用户的 ID
            userId = StpUtil.getLoginIdAsLong();
        } catch (Exception e) {
            // 若没有登录，则直接放行
            return chain.filter(exchange);
        }

        log.info("## HeaderUserId2ContextFilter, 用户 ID: {}", userId);

        Long finalUserId = userId;
        // 通过 mutate() 方法创建一个新的 ServerWebExchange 对象，用于修改当前请求。
        ServerWebExchange newExchange = exchange.mutate()
                // 将用户 ID 设置到请求头中
                .request(builder -> builder.header(GlobalConstants.USER_ID, String.valueOf(finalUserId)))
                .build();
        // 将请求传递给过滤器链中的下一个过滤器进行处理。当前没有对请求进行任何修改。
        return chain.filter(exchange);
    }
}
