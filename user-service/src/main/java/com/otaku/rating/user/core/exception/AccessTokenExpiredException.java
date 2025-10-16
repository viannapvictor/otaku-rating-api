package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.UnauthorizedException;

public final class AccessTokenExpiredException extends UnauthorizedException {
    public AccessTokenExpiredException() {
        super("access.token.expired", "The access token has expired.");
    }
}
