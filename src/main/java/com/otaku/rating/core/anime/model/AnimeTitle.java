package com.otaku.rating.core.anime.model;

import com.otaku.rating.core.generic.exception.ValidationException;
import lombok.Getter;

@Getter
public final class AnimeTitle {
    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 128;

    private final String value;

    private AnimeTitle(String value) {
        this.value = value;
    }

    public static AnimeTitle valueOf(String value) {
        if (value == null) {
            throw new ValidationException("anime.title.null", "The title cannot be null.");
        }
        String sanitizedValue = value.trim();
        if (sanitizedValue.length() < MIN_LENGTH || sanitizedValue.length() > MAX_LENGTH) {
            String message = String.format(
                    "The title size must be greater than or equal to %d and less than or equal to %d.",
                    MIN_LENGTH,
                    MAX_LENGTH
            );
            throw new ValidationException("anime.title.invalid.length", message);
        }
        if (!hasInvalidChars(sanitizedValue)) {
            throw new ValidationException("anime.title.invalid.char", "The title has invalid characters.");
        }
        return new AnimeTitle(sanitizedValue);
    }

    public static AnimeTitle fromInfra(String value) {
        return new AnimeTitle(value);
    }

    private static boolean hasInvalidChars(String value) {
        int whitespaceSequence = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (Character.isISOControl(c)) {
                return true;
            }
            if (c != ' ') {
                if (Character.isWhitespace(c)) return true;

                whitespaceSequence = 0;
                continue;
            }
            whitespaceSequence += 1;
            if (whitespaceSequence > 1) {
                return true;
            }
        }
        return false;
    }
}
