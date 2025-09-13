package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class EmailConfirmationCodeInvalidException extends ValidationException {
    public EmailConfirmationCodeInvalidException() {
        super("email.confirmation.code.invalid", "The email confirmation code is invalid.");
    }
}
