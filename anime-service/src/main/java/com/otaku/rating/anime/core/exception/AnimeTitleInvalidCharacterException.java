package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class AnimeTitleInvalidCharacterException extends ValidationException {
    public AnimeTitleInvalidCharacterException() {
        super("anime.title.invalid.char", "The title has invalid characters.");
    }
}
