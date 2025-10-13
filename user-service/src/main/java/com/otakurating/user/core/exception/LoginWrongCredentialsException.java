package com.otakurating.user.core.exception;

public final class LoginWrongCredentialsException extends UnauthorizedException {
    public LoginWrongCredentialsException() {
        super("login.wrong.credentials", "The account does not exist or the password is wrong.");
    }
}
