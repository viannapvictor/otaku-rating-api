package com.otakurating.anime.core.exception;

public final class AnimeReleaseNullException extends CoreException {
    public AnimeReleaseNullException() {
        super("anime.release.null", "The release cannot be null.");
    }
}
