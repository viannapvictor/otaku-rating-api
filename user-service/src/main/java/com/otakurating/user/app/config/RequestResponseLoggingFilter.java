package com.otakurating.user.app.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class RequestResponseLoggingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger("graylog-only");
    private static final int MAX_PAYLOAD_LENGTH = 10000;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpResponse);

        long startTime = System.currentTimeMillis();

        try {
            chain.doFilter(requestWrapper, responseWrapper);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logRequestResponse(requestWrapper, responseWrapper, duration);
            responseWrapper.copyBodyToResponse();
        }
    }

    private void logRequestResponse(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response, long duration) {
        String requestBody = getContent(request.getContentAsByteArray());
        String responseBody = getContent(response.getContentAsByteArray());

        try {
            MDC.put("http_method", request.getMethod());
            MDC.put("http_uri", request.getRequestURI());
            if (request.getQueryString() != null) {
                MDC.put("http_query_string", request.getQueryString());
            }
            MDC.put("remote_addr", request.getRemoteAddr());
            if (!requestBody.isEmpty()) {
                MDC.put("request_body", requestBody);
            }
            MDC.put("http_status", String.valueOf(response.getStatus()));
            if (!responseBody.isEmpty()) {
                MDC.put("response_body", responseBody);
            }
            MDC.put("duration_ms", String.valueOf(duration));

            logger.info("HTTP Request/Response");
        } finally {
            MDC.remove("http_method");
            MDC.remove("http_uri");
            MDC.remove("http_query_string");
            MDC.remove("remote_addr");
            MDC.remove("request_body");
            MDC.remove("http_status");
            MDC.remove("response_body");
            MDC.remove("duration_ms");
        }
    }

    private String getContent(byte[] content) {
        if (content.length == 0) {
            return "";
        }
        String body = new String(content, StandardCharsets.UTF_8);
        body = sanitizeBody(body);
        if (body.length() > MAX_PAYLOAD_LENGTH) {
            return body.substring(0, MAX_PAYLOAD_LENGTH) + "... [TRUNCATED]";
        }
        return body;
    }

    private String sanitizeBody(String body) {
        body = body.replaceAll("(\"password\"\\s*:\\s*)\"[^\"]*\"", "$1\"***REDACTED***\"");
        body = body.replaceAll("(\"token\"\\s*:\\s*)\"[^\"]*\"", "$1\"***REDACTED***\"");
        body = body.replaceAll("(\"secret\"\\s*:\\s*)\"[^\"]*\"", "$1\"***REDACTED***\"");
        body = body.replaceAll("(\"apiKey\"\\s*:\\s*)\"[^\"]*\"", "$1\"***REDACTED***\"");
        return body;
    }
}