package com.otakurating.rating.core.exception;

public final class ReviewMissingUserIdentifierException extends CoreException {
    public ReviewMissingUserIdentifierException() {
        super("review.missing.user.id", "The review must have an user identifier.");
    }
}
