package com.otaku.rating.core.anime.model;

import com.otaku.rating.core.generic.exception.ValidationException;
import lombok.Getter;

import java.time.LocalDate;
import java.time.ZoneOffset;

@Getter
public final class AnimeRelease {
    public static final int MIN_YEAR = 1900;

    private final LocalDate value;

    private AnimeRelease(LocalDate value) {
        this.value = value;
    }

    public static AnimeRelease valueOf(LocalDate value) {
        if (value == null) {
            throw new ValidationException("anime.release.null", "The release cannot be null.");
        }
        LocalDate now = LocalDate.now(ZoneOffset.UTC);
        if (value.getYear() < MIN_YEAR || value.isAfter(now)) {
            String message = String.format(
                    "The release date must be greater than the year %d and less than or equal to the current date.",
                    MIN_YEAR
            );
            throw new ValidationException("anime.release.invalid", message);
        }
        return new AnimeRelease(value);
    }

    public static AnimeRelease fromInfra(LocalDate value) {
        return new AnimeRelease(value);
    }
}
