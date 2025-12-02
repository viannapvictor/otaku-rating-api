package com.otakurating.rating.core.exception;

public final class RateNullException extends CoreException {
    public RateNullException() {
        super("rate.null", "The rate cannot be null.");
    }
}
