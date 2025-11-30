package com.otakurating.gateway.app.config;

import com.otakurating.gateway.app.dto.UserDTO;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {
    private final WebClient.Builder webClientBuilder;

    public JwtAuthenticationFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String userId = exchange.getRequest().getHeaders().getFirst("X-User-Id");
        String userRole = exchange.getRequest().getHeaders().getFirst("X-User-Role");
        if (userId != null || userRole != null) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return webClientBuilder.build()
                .get()
                .uri("lb://user-service/api/user/me")
                .retrieve()
                .bodyToMono(UserDTO.class)
                .flatMap(user -> {
                    exchange.getRequest().mutate()
                            .header("X-User-Id", user.id())
                            .header("X-User-Role", user.roles().getFirst())
                            .build();
                    return chain.filter(exchange);
                })
                .onErrorResume(e -> chain.filter(exchange));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
