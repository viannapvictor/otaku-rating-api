package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ConflictException;

public final class EmailAlreadyExistsException extends ConflictException {
    public EmailAlreadyExistsException() {
        super("email.already.exists", "The email already exists.");
    }
}
