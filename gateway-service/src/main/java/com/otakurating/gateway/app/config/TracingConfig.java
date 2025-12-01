package com.otakurating.gateway.app.config;

import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.otel.bridge.OtelCurrentTraceContext;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import org.springframework.context.annotation.Bean;

@Configuration
public class TracingConfig {

    @Bean
    public WebFilter tracingMdcFilter(Tracer tracer) {
        return new TracingMdcWebFilter(tracer);
    }

    private static class TracingMdcWebFilter implements WebFilter {
        private final Tracer tracer;

        public TracingMdcWebFilter(Tracer tracer) {
            this.tracer = tracer;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
            return chain.filter(exchange)
                    .contextWrite(context -> {
                        var currentSpan = tracer.currentSpan();

                        if (currentSpan != null) {
                            var spanContext = currentSpan.context();

                            if (spanContext.traceId() != null) {
                                MDC.put("traceId", spanContext.traceId());
                            }
                            if (spanContext.spanId() != null) {
                                MDC.put("spanId", spanContext.spanId());
                            }
                        }

                        return context;
                    })
                    .doFinally(signalType -> {
                        MDC.remove("traceId");
                        MDC.remove("spanId");
                    });
        }
    }
}