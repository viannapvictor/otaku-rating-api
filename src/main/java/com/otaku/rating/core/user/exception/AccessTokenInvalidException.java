package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.UnauthorizedException;

public final class AccessTokenInvalidException extends UnauthorizedException {
    public AccessTokenInvalidException() {
        super("access.token.invalid", "The access token is invalid.");
    }
}
