package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.ConflictException;

public final class AnimeAlreadyExistsException extends ConflictException {
    public AnimeAlreadyExistsException() {
        super("anime.id.conflict", "An anime with the generated id already exists.");
    }
}
