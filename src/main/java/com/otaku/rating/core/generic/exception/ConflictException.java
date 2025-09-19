package com.otaku.rating.core.generic.exception;

public abstract class ConflictException extends CoreException {
    public ConflictException(String code, String message) {
        super(code, message);
    }
}
