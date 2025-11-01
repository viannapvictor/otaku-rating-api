package com.otakurating.gateway.app.config;

import com.otakurating.gateway.app.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {
    private final WebClient.Builder webClientBuilder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || authHeader.isBlank()) {
            return chain.filter(exchange);
        }
        return webClientBuilder.build()
                .get()
                .uri("lb://user-service/api/user/me")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .flatMap(user -> {
                    exchange.getRequest().mutate()
                            .header("X-User-Id", user.getId())
                            .header("X-User-Role", user.getRoles().getFirst())
                            .build();
                    return chain.filter(exchange);
                })
                .onErrorResume(e -> {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
