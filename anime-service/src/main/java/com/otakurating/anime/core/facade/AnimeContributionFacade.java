package com.otakurating.anime.core.facade;

import com.otakurating.anime.core.model.AnimeContribution;

import java.util.UUID;

public interface AnimeContributionFacade {
    AnimeContribution add(AnimeContribution animeContribution);
    AnimeContribution update(AnimeContribution animeContribution);
    void delete(AnimeContribution animeContribution);
    AnimeContribution getById(String animeId, UUID personId);
}
