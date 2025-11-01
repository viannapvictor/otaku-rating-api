package com.otakurating.anime.core.exception;

import com.otakurating.anime.core.model.AnimeTitle;

public final class AnimeTitleInvalidLengthException extends CoreException {
    private static final String MESSAGE = String.format(
            "The title size must be greater than or equal to %d and less than or equal to %d.",
            AnimeTitle.MIN_LENGTH,
            AnimeTitle.MAX_LENGTH
    );

    public AnimeTitleInvalidLengthException() {
        super("anime.title.invalid.length", MESSAGE);
    }
}
