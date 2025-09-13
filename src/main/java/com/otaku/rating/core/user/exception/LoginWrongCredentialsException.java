package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.UnauthorizedException;

public final class LoginWrongCredentialsException extends UnauthorizedException {
    public LoginWrongCredentialsException() {
        super("login.wrong.credentials", "The account does not exist or the password is wrong.");
    }
}
