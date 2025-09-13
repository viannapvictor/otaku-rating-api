package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class EmailConfirmationPendingException extends ValidationException {
    public EmailConfirmationPendingException() {
        super("email.confirmation.pending", "It's necessary to confirm the email before proceeding.");
    }
}
