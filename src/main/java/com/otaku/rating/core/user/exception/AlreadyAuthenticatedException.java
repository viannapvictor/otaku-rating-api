package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class AlreadyAuthenticatedException extends ValidationException {
    public AlreadyAuthenticatedException() {
        super("user.already.logged", "The user is already logged.");
    }
}
