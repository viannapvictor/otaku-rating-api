package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class EmailNullException extends ValidationException {
    public EmailNullException() {
        super("user.email.null", "The user email cannot be null.");
    }
}
