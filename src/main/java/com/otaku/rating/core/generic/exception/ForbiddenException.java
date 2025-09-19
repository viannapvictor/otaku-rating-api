package com.otaku.rating.core.generic.exception;

public final class ForbiddenException extends CoreException {
    public ForbiddenException() {
        super("permission.not.sufficient", "The current user does not have sufficient permission to perform the functionality.");
    }
}
