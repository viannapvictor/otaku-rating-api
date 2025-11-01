package com.otakurating.anime.core.exception;

import com.otakurating.anime.core.model.AnimeRelease;

public final class AnimeReleaseInvalidException extends CoreException {
    private static final String MESSAGE = String.format(
            "The release date must be greater than the year %d and less than or equal to the current date.",
            AnimeRelease.MIN_YEAR
    );

    public AnimeReleaseInvalidException() {
        super("anime.release.invalid", MESSAGE);
    }
}
