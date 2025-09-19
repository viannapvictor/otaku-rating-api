package com.otaku.rating.core.anime.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class AnimeDescriptionNullException extends ValidationException {
    public AnimeDescriptionNullException() {
        super("anime.description.null", "The description cannot be null.");
    }
}
