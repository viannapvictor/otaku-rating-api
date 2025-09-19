package com.otaku.rating.core.anime.exception;

import com.otaku.rating.core.generic.exception.ConflictException;

public final class AnimeAlreadyExistsException extends ConflictException {
    public AnimeAlreadyExistsException() {
        super("anime.id.conflict", "An anime with the generated id already exists.");
    }
}
