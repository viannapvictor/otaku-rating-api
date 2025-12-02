package com.otakurating.rating.core.exception;

import com.otakurating.rating.core.model.Rate;

public final class RateOutOfRangeException extends CoreException {
    private static final String MESSAGE = String.format(
            "The rate must be greater than or equal to %d and less than or equal to %d.",
            Rate.MIN_VALUE,
            Rate.MAX_VALUE
    );

    public RateOutOfRangeException() {
        super("rate.out.of.range", MESSAGE);
    }
}
