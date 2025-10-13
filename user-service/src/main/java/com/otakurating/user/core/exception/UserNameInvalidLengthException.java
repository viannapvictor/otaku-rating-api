package com.otakurating.user.core.exception;

import com.otakurating.user.core.model.valueobject.UserName;

public final class UserNameInvalidLengthException extends CoreException {
    private static final String MESSAGE = String.format(
            "The user username length must be between %d and %d characters",
            UserName.MIN_LENGTH,
            UserName.MAX_LENGTH
    );

    public UserNameInvalidLengthException() {
        super("user.username.invalid.length", MESSAGE);
    }
}
