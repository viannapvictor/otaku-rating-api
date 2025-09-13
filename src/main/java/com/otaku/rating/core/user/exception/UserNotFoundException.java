package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.NotFoundException;

public final class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("user.not.found", "The user was not found.");
    }
}
