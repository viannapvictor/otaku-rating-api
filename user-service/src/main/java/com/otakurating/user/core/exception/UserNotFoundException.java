package com.otakurating.user.core.exception;

public final class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("user.not.found", "The user was not found.");
    }
}
