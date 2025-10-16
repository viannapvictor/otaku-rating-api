package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.UnauthorizedException;

public final class OldPasswordWrongException extends UnauthorizedException {
    public OldPasswordWrongException() {
        super("old.password.wrong", "The old password does not match.");
    }
}
