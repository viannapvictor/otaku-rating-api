package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class EmailNullException extends ValidationException {
    public EmailNullException() {
        super("user.email.null", "The user email cannot be null.");
    }
}
