package com.otaku.rating.core.anime.exception;

import com.otaku.rating.core.anime.model.AnimeRelease;
import com.otaku.rating.core.generic.exception.ValidationException;

public final class AnimeReleaseInvalidException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The release date must be greater than the year %d and less than or equal to the current date.",
            AnimeRelease.MIN_YEAR
    );

    public AnimeReleaseInvalidException() {
        super("anime.release.invalid", MESSAGE);
    }
}
