package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class PasswordResetPendingException extends ValidationException {
    public PasswordResetPendingException() {
        super("password.reset.pending", "It's necessary to confirm the password reset before proceeding.");
    }
}
