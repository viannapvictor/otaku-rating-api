package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class UserNameInvalidCharacterException extends ValidationException {
    public UserNameInvalidCharacterException() {
        super("user.username.invalid.character", "The user username contains invalid characters.");
    }
}
