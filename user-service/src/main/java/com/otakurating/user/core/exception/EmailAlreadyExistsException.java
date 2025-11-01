package com.otakurating.user.core.exception;

public final class EmailAlreadyExistsException extends ConflictException {
    public EmailAlreadyExistsException() {
        super("email.already.exists", "The email already exists.");
    }
}
