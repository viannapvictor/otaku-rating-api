package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;
import com.otaku.rating.user.core.model.valueobject.UserName;

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
