package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.valueobject.Password;

public final class PasswordInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The user email length must be greater than or equal to %d characters.",
            Password.MIN_LENGTH
    );

    public PasswordInvalidLengthException() {
        super("user.password.invalid.length", MESSAGE);
    }
}
