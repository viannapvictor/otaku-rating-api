package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class PasswordInvalidCharacterException extends ValidationException {
    public PasswordInvalidCharacterException() {
        super("user.password.invalid.character", "The password contains invalid characters.");
    }
}
