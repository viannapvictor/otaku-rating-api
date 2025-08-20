package com.otaku.rating.core.generic.exception;

public abstract class ValidationException extends CoreException {
    public ValidationException(String code, String message) {
        super(code, message);
    }
}
