package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ConflictException;

public final class UserNameAlreadyExistsException extends ConflictException {
    public UserNameAlreadyExistsException() {
        super("username.already.exists", "The username already exists.");
    }
}
