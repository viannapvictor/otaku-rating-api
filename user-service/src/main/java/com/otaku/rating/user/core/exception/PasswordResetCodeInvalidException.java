package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class PasswordResetCodeInvalidException extends ValidationException {
    public PasswordResetCodeInvalidException() {
        super("password.reset.code.invalid", "The password reset code is invalid.");
    }
}
