package com.otaku.rating.core.anime.exception;

import com.otaku.rating.core.generic.exception.NotFoundException;

public final class AnimeNotFoundException extends NotFoundException {
    public AnimeNotFoundException() {
        super("anime.not.found", "The anime was not found.");
    }
}
