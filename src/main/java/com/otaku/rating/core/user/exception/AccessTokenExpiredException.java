package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.UnauthorizedException;

public final class AccessTokenExpiredException extends UnauthorizedException {
    public AccessTokenExpiredException() {
        super("access.token.expired", "The access token has expired.");
    }
}
