package com.otakurating.gateway.app.config;

import com.otakurating.gateway.app.dto.ApiResponse;
import com.otakurating.gateway.app.dto.UserDTO;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {
    private final WebClient webClient;

    public JwtAuthenticationFilter(
            @LoadBalanced WebClient.Builder webClientBuilder
    ) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return webClient.get()
                .uri("http://user-service/api/user/me")
                .cookies(c -> exchange.getRequest()
                    .getCookies()
                    .forEach((name, cookieList) ->
                        cookieList.forEach(cookie ->
                            c.add(cookie.getName(), cookie.getValue())
                        )
                    )
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ApiResponse<UserDTO>>() {})
                .flatMap(user -> {
                    ServerHttpRequest newRequest = exchange.getRequest()
                            .mutate()
                            .header("X-User-Id", user.result().id())
                            .header("X-User-Role", user.result().roles() == null ? "" : user.result().roles().getFirst())
                            .build();
                    ServerWebExchange newExchange = exchange.mutate()
                            .request(newRequest)
                            .build();
                    return chain.filter(newExchange);
                })
                .onErrorResume(e -> {
                    ServerHttpRequest newRequest = exchange.getRequest()
                            .mutate()
                            .header("X-User-Id", "")
                            .header("X-User-Role", "")
                            .build();
                    ServerWebExchange newExchange = exchange.mutate()
                            .request(newRequest)
                            .build();
                    return chain.filter(newExchange);
                });
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
