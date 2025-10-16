package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class PasswordInvalidCharacterException extends ValidationException {
    public PasswordInvalidCharacterException() {
        super("user.password.invalid.character", "The password contains invalid characters.");
    }
}
