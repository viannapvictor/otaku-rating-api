package com.otakurating.anime.core.exception;

public final class AnimeNotFoundException extends NotFoundException {
    public AnimeNotFoundException() {
        super("anime.not.found", "The anime was not found.");
    }
}
