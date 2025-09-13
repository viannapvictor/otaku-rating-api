package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class PasswordNullException extends ValidationException {
    public PasswordNullException() {
        super("user.password.null", "The user password cannot be null.");
    }
}
