package com.otaku.rating.core.anime.model;

import com.otaku.rating.core.anime.exception.AnimeDescriptionInvalidLengthException;
import com.otaku.rating.core.anime.exception.AnimeDescriptionNullException;
import lombok.Getter;

@Getter
public final class AnimeDescription {
    public static final int MAX_LENGTH = 512;

    private final String value;

    private AnimeDescription(String value) {
        this.value = value;
    }

    public static AnimeDescription valueOf(String value) {
        if (value == null) {
            throw new AnimeDescriptionNullException();
        }
        String sanitizedValue = value.trim();
        if (sanitizedValue.length() > MAX_LENGTH) {
            throw new AnimeDescriptionInvalidLengthException();
        }
        return new AnimeDescription(sanitizedValue);
    }

    public static AnimeDescription valueOfUnsafe(String value) {
        return new AnimeDescription(value);
    }
}
