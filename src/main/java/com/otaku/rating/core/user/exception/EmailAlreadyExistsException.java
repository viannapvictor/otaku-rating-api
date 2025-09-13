package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ConflictException;

public final class EmailAlreadyExistsException extends ConflictException {
    public EmailAlreadyExistsException() {
        super("email.already.exists", "The email already exists.");
    }
}
