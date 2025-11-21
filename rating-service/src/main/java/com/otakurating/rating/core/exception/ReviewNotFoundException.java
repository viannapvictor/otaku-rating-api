package com.otakurating.rating.core.exception;

public final class ReviewNotFoundException extends NotFoundException {
    public ReviewNotFoundException() {
        super("review.not.found", "The review was not found.");
    }
}
