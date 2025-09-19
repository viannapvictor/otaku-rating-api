package com.otaku.rating.core.anime.repository;

import com.otaku.rating.core.anime.model.AnimeContribution;

import java.util.Optional;
import java.util.UUID;

public interface AnimeContributionRepository {
    AnimeContribution save(AnimeContribution animeContribution);
    boolean exists(String animeId, UUID personId);
    Optional<AnimeContribution> findById(String animeId, UUID personId);
    void delete(AnimeContribution animeContribution);
}
