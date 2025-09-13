package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class NameNullException extends ValidationException {
    public NameNullException() {
        super("user.name.null", "The user name cannot be null.");
    }
}
