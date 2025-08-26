package com.otaku.rating.api.handler;

import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.core.generic.exception.ConflictException;
import com.otaku.rating.core.generic.exception.NotFoundException;
import com.otaku.rating.core.generic.exception.UnauthorizedException;
import com.otaku.rating.core.generic.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<Object>> validationException(ValidationException e) {
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
}
