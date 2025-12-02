package com.otakurating.rating.core.exception;

public final class ReviewAlreadyReviewedException extends ConflictException {
    public ReviewAlreadyReviewedException() {
        super("review.already.reviewed", "A user review of the rating has already been provided.");
    }
}
