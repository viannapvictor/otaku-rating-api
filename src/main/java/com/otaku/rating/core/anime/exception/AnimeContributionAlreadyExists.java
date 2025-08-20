package com.otaku.rating.core.anime.exception;

import com.otaku.rating.core.generic.exception.ConflictException;

public final class AnimeContributionAlreadyExists extends ConflictException {
    public AnimeContributionAlreadyExists() {
        super("anime.contribution.id.conflict", "A contribution already exists between the anime and the person.");
    }
}
