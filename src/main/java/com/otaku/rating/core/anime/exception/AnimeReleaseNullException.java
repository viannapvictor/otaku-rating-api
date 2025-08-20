package com.otaku.rating.core.anime.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class AnimeReleaseNullException extends ValidationException {
    public AnimeReleaseNullException() {
        super("anime.release.null", "The release cannot be null.");
    }
}
