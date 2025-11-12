package com.otakurating.user.core.exception;

public final class EmailNullException extends CoreException {
    public EmailNullException() {
        super("user.email.null", "The user email cannot be null.");
    }
}
