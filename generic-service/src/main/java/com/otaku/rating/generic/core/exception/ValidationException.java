package com.otaku.rating.generic.core.exception;

public abstract class ValidationException extends CoreException {
    public ValidationException(String code, String message) {
        super(code, message);
    }
}
