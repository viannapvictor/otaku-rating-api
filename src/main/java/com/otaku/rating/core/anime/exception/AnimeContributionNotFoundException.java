package com.otaku.rating.core.anime.exception;

import com.otaku.rating.core.generic.exception.NotFoundException;

public final class AnimeContributionNotFoundException extends NotFoundException {
    public AnimeContributionNotFoundException() {
        super("anime.contribution.not.found", "The anime contribution was not found.");
    }
}
