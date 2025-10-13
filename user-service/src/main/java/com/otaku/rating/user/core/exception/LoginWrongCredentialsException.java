package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.UnauthorizedException;

public final class LoginWrongCredentialsException extends UnauthorizedException {
    public LoginWrongCredentialsException() {
        super("login.wrong.credentials", "The account does not exist or the password is wrong.");
    }
}
