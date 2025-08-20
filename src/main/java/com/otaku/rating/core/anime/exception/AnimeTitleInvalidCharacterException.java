package com.otaku.rating.core.anime.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class AnimeTitleInvalidCharacterException extends ValidationException {
    public AnimeTitleInvalidCharacterException() {
        super("anime.title.invalid.char", "The title has invalid characters.");
    }
}
