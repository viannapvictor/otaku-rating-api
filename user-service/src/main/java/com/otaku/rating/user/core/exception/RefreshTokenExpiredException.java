package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.UnauthorizedException;

public final class RefreshTokenExpiredException extends UnauthorizedException {
    public RefreshTokenExpiredException() {
        super("refresh.token.expired", "The refresh token has expired.");
    }
}
