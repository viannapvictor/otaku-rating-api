package com.otakurating.rating.core.exception;

public final class RatingAlreadyExistsException extends ConflictException {
    public RatingAlreadyExistsException() {
        super("rating.already.exists", "A rating with the specified identifier already exists.");
    }
}
