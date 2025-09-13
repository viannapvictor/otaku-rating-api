package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class UserNameInvalidFormatException extends ValidationException {
    public UserNameInvalidFormatException() {
        super("user.username.invalid.format", "The user username cannot start or end with '-'");
    }
}
