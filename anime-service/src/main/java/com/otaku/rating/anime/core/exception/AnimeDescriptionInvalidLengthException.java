package com.otaku.rating.anime.core.exception;

import com.otaku.rating.anime.core.model.AnimeDescription;
import com.otaku.rating.generic.core.exception.ValidationException;

public final class AnimeDescriptionInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The description size must be less than or equal to %d.",
            AnimeDescription.MAX_LENGTH
    );

    public AnimeDescriptionInvalidLengthException() {
        super("anime.description.invalid.length", MESSAGE);
    }
}
