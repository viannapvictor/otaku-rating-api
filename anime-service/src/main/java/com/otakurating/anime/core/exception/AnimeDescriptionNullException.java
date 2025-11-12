package com.otakurating.anime.core.exception;

public final class AnimeDescriptionNullException extends CoreException {
    public AnimeDescriptionNullException() {
        super("anime.description.null", "The description cannot be null.");
    }
}
