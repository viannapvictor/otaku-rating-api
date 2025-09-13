package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class UserNameInvalidCharacterException extends ValidationException {
    public UserNameInvalidCharacterException() {
        super("user.username.invalid.character", "The user username contains invalid characters.");
    }
}
