package com.otakurating.user.core.exception;

import lombok.Getter;

@Getter
public abstract class CoreException extends RuntimeException {
    private final String code;

    public CoreException(String code, String message) {
        super(message);
        this.code = code;
    }
}
