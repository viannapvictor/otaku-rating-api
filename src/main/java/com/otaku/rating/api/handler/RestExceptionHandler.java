package com.otaku.rating.api.handler;

import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.core.generic.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> notFoundException(NotFoundException e) {
        return ApiResponse.error(e).createResponse(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Object>> unauthorizedException(UnauthorizedException e) {
        return ApiResponse.error(e).createResponse(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<Object>> conflictException(ConflictException e) {
        return ApiResponse.error(e).createResponse(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<ApiResponse<Object>> coreException(CoreException e) {
        return ApiResponse.error(e).createResponse(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<Object>> forbiddenException(ForbiddenException e) {
        return ApiResponse.error(e).createResponse(HttpStatus.FORBIDDEN);
    }
}
