package com.otakurating.rating.core.exception;

public final class RatingMissingIdentifierException extends CoreException {
    public RatingMissingIdentifierException() {
        super("rating.missing.id", "The rating identifier must not be null.");
    }
}
