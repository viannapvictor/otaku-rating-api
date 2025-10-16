package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.UnauthorizedException;

public final class AccessTokenInvalidException extends UnauthorizedException {
    public AccessTokenInvalidException() {
        super("access.token.invalid", "The access token is invalid.");
    }
}
