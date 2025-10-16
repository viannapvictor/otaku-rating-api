package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;
import com.otaku.rating.user.core.model.valueobject.Name;

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
