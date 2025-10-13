package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.UnauthorizedException;

public final class RefreshTokenInvalidException extends UnauthorizedException {
    public RefreshTokenInvalidException() {
        super("refresh.token.invalid", "The refresh token is invalid.");
    }
}
