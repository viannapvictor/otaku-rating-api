package com.otakurating.user.core.exception;

public final class OldPasswordWrongException extends UnauthorizedException {
    public OldPasswordWrongException() {
        super("old.password.wrong", "The old password does not match.");
    }
}
