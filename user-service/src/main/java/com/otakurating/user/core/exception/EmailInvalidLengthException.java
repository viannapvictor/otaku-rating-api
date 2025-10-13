package com.otakurating.user.core.exception;

import com.otakurating.user.core.model.valueobject.Email;

public final class EmailInvalidLengthException extends CoreException {
    private static final String MESSAGE = String.format(
            "The user email length must be between %d and %d characters.",
            Email.MIN_LENGTH,
            Email.MAX_LENGTH
    );

    public EmailInvalidLengthException() {
        super("user.email.invalid.length", MESSAGE);
    }
}
