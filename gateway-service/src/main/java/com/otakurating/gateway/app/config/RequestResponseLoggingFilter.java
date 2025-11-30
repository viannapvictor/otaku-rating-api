package com.otakurating.gateway.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RequestResponseLoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger("graylog-only");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String method = request.getMethod().name();
        String uri = request.getURI().toString();
        String path = request.getURI().getPath();
        String remoteAddr = request.getRemoteAddress() != null ?
                request.getRemoteAddress().getAddress().getHostAddress() : "unknown";
        long startTime = System.currentTimeMillis();

        return chain.filter(exchange).doFinally(signalType -> {
            ServerHttpResponse response = exchange.getResponse();
            int status = response.getStatusCode() != null ? response.getStatusCode().value() : 0;
            long duration = System.currentTimeMillis() - startTime;

            try {
                MDC.put("http_method", method);
                MDC.put("http_uri", uri);
                MDC.put("http_path", path);
                MDC.put("remote_addr", remoteAddr);
                MDC.put("http_status", String.valueOf(status));
                MDC.put("duration_ms", String.valueOf(duration));

                logger.info("HTTP Gateway Request/Response");
            } finally {
                MDC.remove("http_method");
                MDC.remove("http_uri");
                MDC.remove("http_path");
                MDC.remove("remote_addr");
                MDC.remove("http_status");
                MDC.remove("duration_ms");
            }
        });
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}