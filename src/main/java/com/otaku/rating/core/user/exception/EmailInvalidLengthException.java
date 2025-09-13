package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.valueobject.Email;

public final class EmailInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The user email length must be between %d and %d characters.",
            Email.MIN_LENGTH,
            Email.MAX_LENGTH
    );

    public EmailInvalidLengthException() {
        super("user.email.invalid.length", MESSAGE);
    }
}
