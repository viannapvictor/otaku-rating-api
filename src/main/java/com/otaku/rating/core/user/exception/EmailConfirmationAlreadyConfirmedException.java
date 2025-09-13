package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class EmailConfirmationAlreadyConfirmedException extends ValidationException {
    public EmailConfirmationAlreadyConfirmedException() {
        super("email.confirmation.already.confirmed", "The email is already confirmed.");
    }
}
