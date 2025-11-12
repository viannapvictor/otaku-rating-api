package com.otakurating.user.core.exception;

public final class NameInvalidCharacterException extends CoreException {
    public NameInvalidCharacterException() {
        super("user.name.invalid.character", "The user name contains invalid characters.");
    }
}
