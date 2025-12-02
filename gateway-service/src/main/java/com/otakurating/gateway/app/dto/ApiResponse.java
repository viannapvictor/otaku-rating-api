package com.otakurating.gateway.app.dto;

public record ApiResponse<T>(boolean success, T result, ErrorData error) {
}
