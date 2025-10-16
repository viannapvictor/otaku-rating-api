package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class EmailConfirmationAlreadyConfirmedException extends ValidationException {
    public EmailConfirmationAlreadyConfirmedException() {
        super("email.confirmation.already.confirmed", "The email is already confirmed.");
    }
}
