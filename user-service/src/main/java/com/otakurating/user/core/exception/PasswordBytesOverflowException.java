package com.otakurating.user.core.exception;

public final class PasswordBytesOverflowException extends CoreException {
    public PasswordBytesOverflowException() {
        super("user.password.bytes.overflow", "The user password has exceeds the maximum allowed bytes.");
    }
}
