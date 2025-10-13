package com.otakurating.user.core.exception;

public abstract class UnauthorizedException extends CoreException {
    public UnauthorizedException(String code, String message) {
        super(code, message);
    }
}
