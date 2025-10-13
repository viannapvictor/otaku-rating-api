package com.otaku.rating.anime.core.exception;

import com.otaku.rating.anime.core.model.AnimeRelease;
import com.otaku.rating.generic.core.exception.ValidationException;

public final class AnimeReleaseInvalidException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The release date must be greater than the year %d and less than or equal to the current date.",
            AnimeRelease.MIN_YEAR
    );

    public AnimeReleaseInvalidException() {
        super("anime.release.invalid", MESSAGE);
    }
}
