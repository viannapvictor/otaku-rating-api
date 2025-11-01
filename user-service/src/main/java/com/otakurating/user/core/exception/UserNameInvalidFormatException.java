package com.otakurating.user.core.exception;

public final class UserNameInvalidFormatException extends CoreException {
    public UserNameInvalidFormatException() {
        super("user.username.invalid.format", "The user username cannot start or end with '-'");
    }
}
