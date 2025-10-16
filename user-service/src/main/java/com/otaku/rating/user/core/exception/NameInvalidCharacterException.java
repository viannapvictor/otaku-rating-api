package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class NameInvalidCharacterException extends ValidationException {
    public NameInvalidCharacterException() {
        super("user.name.invalid.character", "The user name contains invalid characters.");
    }
}
