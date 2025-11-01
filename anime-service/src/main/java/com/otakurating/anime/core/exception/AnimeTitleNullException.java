package com.otakurating.anime.core.exception;

public final class AnimeTitleNullException extends CoreException {
    public AnimeTitleNullException() {
        super("anime.title.null", "The title cannot be null.");
    }
}
