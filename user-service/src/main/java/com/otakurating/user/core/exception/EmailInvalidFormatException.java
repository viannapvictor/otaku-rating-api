package com.otakurating.user.core.exception;

public final class EmailInvalidFormatException extends CoreException {
    public EmailInvalidFormatException() {
        super("user.email.invalid.format", "The user email is not a valid email.");
    }
}
