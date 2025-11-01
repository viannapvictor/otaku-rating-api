package com.otakurating.user.core.exception;

public final class NameNullException extends CoreException {
    public NameNullException() {
        super("user.name.null", "The user name cannot be null.");
    }
}
