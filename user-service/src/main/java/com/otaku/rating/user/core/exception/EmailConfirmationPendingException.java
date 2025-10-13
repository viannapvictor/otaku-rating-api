package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class EmailConfirmationPendingException extends ValidationException {
    public EmailConfirmationPendingException() {
        super("email.confirmation.pending", "It's necessary to confirm the email before proceeding.");
    }
}
