package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class PasswordBytesOverflowException extends ValidationException {
    public PasswordBytesOverflowException() {
        super("user.password.bytes.overflow", "The user password has exceeds the maximum allowed bytes.");
    }
}
