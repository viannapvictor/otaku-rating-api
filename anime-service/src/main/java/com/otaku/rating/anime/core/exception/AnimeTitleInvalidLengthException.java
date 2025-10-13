package com.otaku.rating.anime.core.exception;

import com.otaku.rating.anime.core.model.AnimeTitle;
import com.otaku.rating.generic.core.exception.ValidationException;

public final class AnimeTitleInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The title size must be greater than or equal to %d and less than or equal to %d.",
            AnimeTitle.MIN_LENGTH,
            AnimeTitle.MAX_LENGTH
    );

    public AnimeTitleInvalidLengthException() {
        super("anime.title.invalid.length", MESSAGE);
    }
}
