package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class UserNameInvalidFormatException extends ValidationException {
    public UserNameInvalidFormatException() {
        super("user.username.invalid.format", "The user username cannot start or end with '-'");
    }
}
