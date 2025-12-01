package com.otakurating.anime.app.config;

import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.otel.bridge.OtelCurrentTraceContext;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@Configuration
public class TracingConfig {

    @Bean
    public FilterRegistrationBean<TracingMdcFilter> tracingMdcFilter(Tracer tracer) {
        FilterRegistrationBean<TracingMdcFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TracingMdcFilter(tracer));
        registrationBean.setOrder(Integer.MIN_VALUE);
        return registrationBean;
    }

    private static class TracingMdcFilter extends OncePerRequestFilter {
        private final Tracer tracer;

        public TracingMdcFilter(Tracer tracer) {
            this.tracer = tracer;
        }

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            try {
                var currentSpan = tracer.currentSpan();

                if (currentSpan != null) {
                    var context = currentSpan.context();

                    if (context.traceId() != null) {
                        MDC.put("traceId", context.traceId());
                    }
                    if (context.spanId() != null) {
                        MDC.put("spanId", context.spanId());
                    }
                }

                filterChain.doFilter(request, response);

            } finally {
                MDC.remove("traceId");
                MDC.remove("spanId");
            }
        }
    }
}