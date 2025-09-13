package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class PasswordResetCodeInvalidException extends ValidationException {
    public PasswordResetCodeInvalidException() {
        super("password.reset.code.invalid", "The password reset code is invalid.");
    }
}
