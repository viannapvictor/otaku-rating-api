package com.otakurating.anime.core.model;

import com.otakurating.anime.core.exception.AnimeDescriptionInvalidLengthException;
import com.otakurating.anime.core.exception.AnimeDescriptionNullException;

public final class AnimeDescription {
    public static final int MAX_LENGTH = 512;

    private final String value;

    private AnimeDescription(String value) {
        if (value == null) {
            throw new AnimeDescriptionNullException();
        }
        String sanitizedValue = value.trim();
        if (sanitizedValue.length() > MAX_LENGTH) {
            throw new AnimeDescriptionInvalidLengthException();
        }
        this.value = sanitizedValue;
    }

    public String getValue() {
        return value;
    }

    public static AnimeDescription valueOf(String value) {
        return new AnimeDescription(value);
    }
}
