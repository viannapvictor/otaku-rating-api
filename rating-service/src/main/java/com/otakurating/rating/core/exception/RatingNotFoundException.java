package com.otakurating.rating.core.exception;

public final class RatingNotFoundException extends NotFoundException {
    public RatingNotFoundException() {
        super("rating.not.found", "The rating was not found.");
    }
}
