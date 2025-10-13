package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;
import com.otaku.rating.user.core.model.valueobject.Password;

public final class PasswordInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The user email length must be greater than or equal to %d characters.",
            Password.MIN_LENGTH
    );

    public PasswordInvalidLengthException() {
        super("user.password.invalid.length", MESSAGE);
    }
}
