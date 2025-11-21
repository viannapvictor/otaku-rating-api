package com.otakurating.rating.core.model;

import com.otakurating.rating.core.exception.RateNullException;
import com.otakurating.rating.core.exception.RateOutOfRangeException;

public final class Rate {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 10;

    private final int value;

    private Rate(Integer value) {
        if (value == null) {
            throw new RateNullException();
        }
        if (value < MIN_VALUE || value > MAX_VALUE) {
            throw new RateOutOfRangeException();
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Rate valueOf(Integer value) {
        return new Rate(value);
    }
}
