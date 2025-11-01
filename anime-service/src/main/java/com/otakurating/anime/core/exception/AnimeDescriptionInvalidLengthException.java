package com.otakurating.anime.core.exception;

import com.otakurating.anime.core.model.AnimeDescription;

public final class AnimeDescriptionInvalidLengthException extends CoreException {
    private static final String MESSAGE = String.format(
            "The description size must be less than or equal to %d.",
            AnimeDescription.MAX_LENGTH
    );

    public AnimeDescriptionInvalidLengthException() {
        super("anime.description.invalid.length", MESSAGE);
    }
}
