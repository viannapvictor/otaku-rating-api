package com.otakurating.user.core.exception;

public final class UserNameAlreadyExistsException extends ConflictException {
    public UserNameAlreadyExistsException() {
        super("username.already.exists", "The username already exists.");
    }
}
