package com.otakurating.anime.core.port.out;

import com.otakurating.anime.core.model.AnimeContribution;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FindAnimeContributionPort {
    Optional<AnimeContribution> findByAnimeIdAndPersonId(String animeId, UUID personId);
    List<AnimeContribution> findByAnimeId(String animeId);
    boolean existsByAnimeIdAndPersonId(String animeId, UUID personId);
}
