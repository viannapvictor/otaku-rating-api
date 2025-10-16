package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class PasswordBytesOverflowException extends ValidationException {
    public PasswordBytesOverflowException() {
        super("user.password.bytes.overflow", "The user password has exceeds the maximum allowed bytes.");
    }
}
