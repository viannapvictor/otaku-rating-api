package com.otaku.rating.core.anime.exception;

import com.otaku.rating.core.anime.model.AnimeDescription;
import com.otaku.rating.core.generic.exception.ValidationException;

public final class AnimeDescriptionInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The description size must be less than or equal to %d.",
            AnimeDescription.MAX_LENGTH
    );

    public AnimeDescriptionInvalidLengthException() {
        super("anime.description.invalid.length", MESSAGE);
    }
}
