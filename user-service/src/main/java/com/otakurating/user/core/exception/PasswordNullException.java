package com.otakurating.user.core.exception;

public final class PasswordNullException extends CoreException {
    public PasswordNullException() {
        super("user.password.null", "The user password cannot be null.");
    }
}
