package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.UnauthorizedException;

public final class RefreshTokenExpiredException extends UnauthorizedException {
    public RefreshTokenExpiredException() {
        super("refresh.token.expired", "The refresh token has expired.");
    }
}
