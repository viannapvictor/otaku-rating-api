package com.otakurating.user.core.exception;

public final class UserNameInvalidCharacterException extends CoreException {
    public UserNameInvalidCharacterException() {
        super("user.username.invalid.character", "The user username contains invalid characters.");
    }
}
