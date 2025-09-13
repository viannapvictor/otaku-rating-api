package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.UnauthorizedException;

public final class OldPasswordWrongException extends UnauthorizedException {
    public OldPasswordWrongException() {
        super("old.password.wrong", "The old password does not match.");
    }
}
