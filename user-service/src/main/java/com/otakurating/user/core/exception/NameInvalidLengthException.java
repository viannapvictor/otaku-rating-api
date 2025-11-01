package com.otakurating.user.core.exception;

import com.otakurating.user.core.model.valueobject.Name;

public final class NameInvalidLengthException extends CoreException {
    private static final String MESSAGE = String.format(
            "The user name length must be between %d and %d characters",
            Name.MIN_LENGTH,
            Name.MAX_LENGTH
    );

    public NameInvalidLengthException() {
        super("user.name.invalid.length", MESSAGE);
    }
}
