package com.otakurating.anime.core.port.out;

import java.util.UUID;

public interface DeleteAnimeContributionPort {
    void deleteByAnimeIdAndPersonId(UUID animeId, UUID personId);
    void deleteByAnimeId(UUID animeId);
    void deleteByPersonId(UUID personId);
}
