package com.otakurating.user.core.exception;

public abstract class NotFoundException extends CoreException {
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
