package com.otakurating.rating.core.exception;

public final class ReviewMissingRatingIdentifierException extends CoreException {
    public ReviewMissingRatingIdentifierException() {
        super("review.missing.rating.id", "The review must have a rating identifier.");
    }
}
