package com.otaku.rating.core.anime.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class AnimeTitleNullException extends ValidationException {
    public AnimeTitleNullException() {
        super("anime.title.null", "The title cannot be null.");
    }
}
