package com.otaku.rating.core.generic.exception;

public class UnauthorizedException extends CoreException {
    public UnauthorizedException(String code, String message) {
        super(code, message);
    }
}
