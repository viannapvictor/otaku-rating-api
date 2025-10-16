package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class UserNameNullException extends ValidationException {
    public UserNameNullException() {
        super("user.username.null", "The user username cannot be null.");
    }
}
