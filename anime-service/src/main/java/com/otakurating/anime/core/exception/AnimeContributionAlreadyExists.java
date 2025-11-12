package com.otakurating.anime.core.exception;

public final class AnimeContributionAlreadyExists extends ConflictException {
    public AnimeContributionAlreadyExists() {
        super("anime.contribution.id.conflict", "A contribution already exists between the anime and the person.");
    }
}
