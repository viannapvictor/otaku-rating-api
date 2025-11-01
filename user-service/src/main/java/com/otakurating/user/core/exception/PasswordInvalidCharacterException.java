package com.otakurating.user.core.exception;

public final class PasswordInvalidCharacterException extends CoreException {
    public PasswordInvalidCharacterException() {
        super("user.password.invalid.character", "The password contains invalid characters.");
    }
}
