package com.otakurating.anime.core.exception;

public final class AnimeAlreadyExistsException extends ConflictException {
    public AnimeAlreadyExistsException() {
        super("anime.id.conflict", "An anime with the generated id already exists.");
    }
}
