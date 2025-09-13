package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class UserNameNullException extends ValidationException {
    public UserNameNullException() {
        super("user.username.null", "The user username cannot be null.");
    }
}
