package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class PasswordResetPendingException extends ValidationException {
    public PasswordResetPendingException() {
        super("password.reset.pending", "It's necessary to confirm the password reset before proceeding.");
    }
}
