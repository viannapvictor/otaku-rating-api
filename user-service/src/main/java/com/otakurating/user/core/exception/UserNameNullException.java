package com.otakurating.user.core.exception;

public final class UserNameNullException extends CoreException {
    public UserNameNullException() {
        super("user.username.null", "The user username cannot be null.");
    }
}
