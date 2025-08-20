package com.otaku.rating.core.anime.model;

import com.otaku.rating.core.generic.exception.ValidationException;
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
            throw new ValidationException("anime.description.null", "The description cannot be null.");
        }
        String sanitizedValue = value.trim();
        if (sanitizedValue.length() > MAX_LENGTH) {
            String message = String.format("The description size must be less than or equal to %d.", MAX_LENGTH);
            throw new ValidationException("anime.description.invalid.length", message);
        }
        return new AnimeDescription(sanitizedValue);
    }

    public static AnimeDescription fromInfra(String value) {
        return new AnimeDescription(value);
    }
}
