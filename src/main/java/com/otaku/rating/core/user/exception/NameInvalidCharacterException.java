package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class NameInvalidCharacterException extends ValidationException {
    public NameInvalidCharacterException() {
        super("user.name.invalid.character", "The user name contains invalid characters.");
    }
}
