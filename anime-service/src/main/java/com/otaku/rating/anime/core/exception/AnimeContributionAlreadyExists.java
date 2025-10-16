package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.ConflictException;

public final class AnimeContributionAlreadyExists extends ConflictException {
    public AnimeContributionAlreadyExists() {
        super("anime.contribution.id.conflict", "A contribution already exists between the anime and the person.");
    }
}
