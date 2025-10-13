package com.otaku.rating.user.core.exception;

import com.otaku.rating.generic.core.exception.UnauthorizedException;

public final class AuthenticationRequiredException extends UnauthorizedException {
    public AuthenticationRequiredException() {
        super("authentication.required", "Authentication is required to access this feature.");
    }
}
