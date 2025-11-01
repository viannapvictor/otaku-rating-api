package com.otakurating.user.app.handler;

import com.otakurating.user.app.response.dto.ApiResponse;
import com.otakurating.user.core.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(CoreException.class)
    public ResponseEntity<ApiResponse<Object>> validationException(CoreException e) {
        return ApiResponse.error(e).createResponse(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> notFoundException(NotFoundException e) {
        return ApiResponse.error(e).createResponse(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponse<Object>> unauthorizedException(UnauthorizedException e) {
        return ApiResponse.error(e).createResponse(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<Object>> unauthorizedException(ConflictException e) {
        return ApiResponse.error(e).createResponse(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiResponse<Object>> forbiddenException(ForbiddenException e) {
        return ApiResponse.error(e).createResponse(HttpStatus.FORBIDDEN);
    }
}
