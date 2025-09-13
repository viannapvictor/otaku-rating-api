package com.otaku.rating.core.user.exception;

import com.otaku.rating.core.generic.exception.UnauthorizedException;

public final class AuthenticationRequiredException extends UnauthorizedException {
    public AuthenticationRequiredException() {
        super("authentication.required", "Authentication is required to access this feature.");
    }
}
