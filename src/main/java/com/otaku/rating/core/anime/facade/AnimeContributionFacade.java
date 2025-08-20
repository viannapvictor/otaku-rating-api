package com.otaku.rating.core.anime.facade;

import com.otaku.rating.core.anime.model.AnimeContribution;

import java.util.UUID;

public interface AnimeContributionFacade {
    AnimeContribution add(AnimeContribution animeContribution);
    AnimeContribution update(AnimeContribution animeContribution);
    void delete(AnimeContribution animeContribution);
    AnimeContribution getById(String animeId, UUID personId);
}
