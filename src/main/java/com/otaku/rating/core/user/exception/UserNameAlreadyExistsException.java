package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ConflictException;

public final class UserNameAlreadyExistsException extends ConflictException {
    public UserNameAlreadyExistsException() {
        super("username.already.exists", "The username already exists.");
    }
}
