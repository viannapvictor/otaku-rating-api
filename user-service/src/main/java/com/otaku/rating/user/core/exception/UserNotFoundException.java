package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.NotFoundException;

public final class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("user.not.found", "The user was not found.");
    }
}
