package com.otakurating.user.core.exception;

import com.otakurating.user.core.model.valueobject.Password;

public final class PasswordInvalidLengthException extends CoreException {
    private static final String MESSAGE = String.format(
            "The user email length must be greater than or equal to %d characters.",
            Password.MIN_LENGTH
    );

    public PasswordInvalidLengthException() {
        super("user.password.invalid.length", MESSAGE);
    }
}
