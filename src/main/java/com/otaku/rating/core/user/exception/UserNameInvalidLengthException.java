package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.valueobject.UserName;

public final class UserNameInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The user username length must be between %d and %d characters",
            UserName.MIN_LENGTH,
            UserName.MAX_LENGTH
    );

    public UserNameInvalidLengthException() {
        super("user.username.invalid.length", MESSAGE);
    }
}
