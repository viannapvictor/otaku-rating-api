package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class EmailInvalidFormatException extends ValidationException {
    public EmailInvalidFormatException() {
        super("user.email.invalid.format", "The user email is not a valid email.");
    }
}
