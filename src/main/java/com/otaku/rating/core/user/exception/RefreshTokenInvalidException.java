package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.UnauthorizedException;

public final class RefreshTokenInvalidException extends UnauthorizedException {
    public RefreshTokenInvalidException() {
        super("refresh.token.invalid", "The refresh token is invalid.");
    }
}
