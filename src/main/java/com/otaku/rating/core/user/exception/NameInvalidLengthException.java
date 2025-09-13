package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.valueobject.Name;

public final class NameInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The user name length must be between %d and %d characters",
            Name.MIN_LENGTH,
            Name.MAX_LENGTH
    );

    public NameInvalidLengthException() {
        super("user.name.invalid.length", MESSAGE);
    }
}
