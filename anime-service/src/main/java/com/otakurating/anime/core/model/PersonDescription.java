package com.otakurating.anime.core.model;

import com.otakurating.anime.core.exception.PersonDescriptionInvalidLengthException;
import com.otakurating.anime.core.exception.PersonDescriptionNullException;

public final class PersonDescription {
    public static final int MAX_LENGTH = 512;

    private final String value;

    private PersonDescription(String value) {
        if (value == null) {
            throw new PersonDescriptionNullException();
        }
        String sanitizedValue = value.trim();
        if (sanitizedValue.length() > MAX_LENGTH) {
            throw new PersonDescriptionInvalidLengthException();
        }
        this.value = sanitizedValue;
    }

    public String getValue() {
        return value;
    }

    public static PersonDescription valueOf(String value) {
        return new PersonDescription(value);
    }
}
