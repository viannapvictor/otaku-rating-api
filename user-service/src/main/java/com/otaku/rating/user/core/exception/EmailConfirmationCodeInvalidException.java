package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class EmailConfirmationCodeInvalidException extends ValidationException {
    public EmailConfirmationCodeInvalidException() {
        super("email.confirmation.code.invalid", "The email confirmation code is invalid.");
    }
}
