package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.NotFoundException;

public final class AnimeNotFoundException extends NotFoundException {
    public AnimeNotFoundException() {
        super("anime.not.found", "The anime was not found.");
    }
}
