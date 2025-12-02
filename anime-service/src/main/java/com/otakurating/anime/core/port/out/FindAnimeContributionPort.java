package com.otakurating.anime.core.port.out;

import com.otakurating.anime.core.model.AnimeContribution;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindAnimeContributionPort {
    Optional<AnimeContribution> findByAnimeIdAndPersonId(UUID animeId, UUID personId);
    List<AnimeContribution> findByAnimeId(UUID animeId);
    boolean existsByAnimeIdAndPersonId(UUID animeId, UUID personId);
}
