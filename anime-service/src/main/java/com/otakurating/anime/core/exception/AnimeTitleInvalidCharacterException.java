package com.otakurating.anime.core.exception;

public final class AnimeTitleInvalidCharacterException extends CoreException {
    public AnimeTitleInvalidCharacterException() {
        super("anime.title.invalid.char", "The title has invalid characters.");
    }
}
