package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class PasswordNullException extends ValidationException {
    public PasswordNullException() {
        super("user.password.null", "The user password cannot be null.");
    }
}
