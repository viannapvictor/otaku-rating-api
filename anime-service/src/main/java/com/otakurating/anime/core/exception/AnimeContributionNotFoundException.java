package com.otakurating.anime.core.exception;

public final class AnimeContributionNotFoundException extends NotFoundException {
    public AnimeContributionNotFoundException() {
        super("anime.contribution.not.found", "The anime contribution was not found.");
    }
}
