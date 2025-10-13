package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class AnimeTitleNullException extends ValidationException {
    public AnimeTitleNullException() {
        super("anime.title.null", "The title cannot be null.");
    }
}
