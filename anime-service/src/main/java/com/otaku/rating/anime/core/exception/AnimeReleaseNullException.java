package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class AnimeReleaseNullException extends ValidationException {
    public AnimeReleaseNullException() {
        super("anime.release.null", "The release cannot be null.");
    }
}
