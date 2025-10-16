package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.NotFoundException;

public final class AnimeContributionNotFoundException extends NotFoundException {
    public AnimeContributionNotFoundException() {
        super("anime.contribution.not.found", "The anime contribution was not found.");
    }
}
