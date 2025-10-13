package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class EmailInvalidFormatException extends ValidationException {
    public EmailInvalidFormatException() {
        super("user.email.invalid.format", "The user email is not a valid email.");
    }
}
