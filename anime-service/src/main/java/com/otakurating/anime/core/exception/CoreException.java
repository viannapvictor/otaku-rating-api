package com.otakurating.anime.core.exception;

public abstract class CoreException extends RuntimeException {
    private final String code;

    public CoreException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
