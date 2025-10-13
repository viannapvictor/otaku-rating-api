package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class NameNullException extends ValidationException {
    public NameNullException() {
        super("user.name.null", "The user name cannot be null.");
    }
}
