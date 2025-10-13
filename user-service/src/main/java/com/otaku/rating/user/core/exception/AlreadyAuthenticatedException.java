package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class AlreadyAuthenticatedException extends ValidationException {
    public AlreadyAuthenticatedException() {
        super("user.already.logged", "The user is already logged.");
    }
}
