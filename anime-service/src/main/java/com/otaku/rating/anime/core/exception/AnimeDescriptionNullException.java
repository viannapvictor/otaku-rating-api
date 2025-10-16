package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class AnimeDescriptionNullException extends ValidationException {
    public AnimeDescriptionNullException() {
        super("anime.description.null", "The description cannot be null.");
    }
}
